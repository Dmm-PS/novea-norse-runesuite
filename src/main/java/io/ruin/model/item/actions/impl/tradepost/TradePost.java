package io.ruin.model.item.actions.impl.tradepost;

import com.google.gson.annotations.Expose;
import io.ruin.api.utils.NumberUtils;
import io.ruin.cache.ItemDef;
import io.ruin.model.World;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.InterfaceAction;
import io.ruin.model.inter.InterfaceHandler;
import io.ruin.model.inter.InterfaceType;
import io.ruin.model.inter.actions.SimpleAction;
import io.ruin.model.item.Item;
import io.ruin.model.map.object.GameObject;
import io.ruin.model.map.object.actions.ObjectAction;
import io.ruin.network.central.CentralClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author <a href="https://github.com/kLeptO">Augustinas R.</a>
 */
public class TradePost {

    private static final int MAX_MY_OFFERS = 6;
    private static final int MAX_VIEW_OFFERS = 20;

    private static final int VIEW_OFFERS_WIDGET = 710;
    private static final int MY_OFFERS_WIDGET = 711;
    private static final int MY_OFFERS_INVENTORY_WIDGET = 712;

    private Player player;
    private String searchText;
    private TradePostSort sort = TradePostSort.AGE_ASCENDING;

    @Expose
    private List<TradePostOffer> myOffers = new ArrayList<>();
    private List<TradePostOffer> viewOffers = new ArrayList<>();

    public void init(Player player) {
        this.player = player;
    }

    public void openViewOffers() {
        player.openInterface(InterfaceType.MAIN, VIEW_OFFERS_WIDGET);
        player.closeInterface(InterfaceType.INVENTORY);
        resetSearch();
    }

    public void openMyOffers() {
        player.openInterface(InterfaceType.MAIN, MY_OFFERS_WIDGET);
        changeInventoryAccess();
        updateMyOffers();
    }

    private void promptCreateOffer(int itemId) {
        if (myOffers.size() > 5) {
            player.sendMessage("You cannot create more offers.");
            return;
        }

        ItemDef itemDef = ItemDef.get(itemId);
        if (itemDef == null || !itemDef.tradeable || itemId == 995) {
            player.sendMessage("You cannot trade this item.");
            return;
        }

        final int unnotedId = !itemDef.isNote() ? itemId : itemDef.fromNote().id;

        if (myOffers.stream().anyMatch(offer -> offer.getItem().getId() == unnotedId)) {
            player.sendMessage("You already have offer for this item.");
            return;
        }

        player.integerInput("Enter item amount you would like to sell:", amount -> {
            player.integerInput("Enter price per item:", price -> {
                myOffers.add(
                        new TradePostOffer(
                                player.getName(),
                                new Item(unnotedId, amount),
                                price,
                                System.currentTimeMillis()
                        )

                );
                updateMyOffers();
            });
        });
    }

    private void promptAdjustOffer(int index) {
        if (index >= myOffers.size()) {
            return;
        }

        player.integerInput("Enter item amount you would like to sell:", amount -> {
            player.integerInput("Enter price per item:", price -> {
                TradePostOffer offer = myOffers.get(index);
                myOffers.set(index,
                        new TradePostOffer(
                                player.getName(),
                                new Item(offer.getItem().getId(), amount),
                                price,
                                offer.getTimestamp()
                        )
                );
                updateMyOffers();
            });
        });
    }

    private void resetOffer(int index) {
        if (index >= myOffers.size()) {
            return;
        }

        myOffers.remove(index);
        updateMyOffers();
    }

    private void changeInventoryAccess() {
        player.openInterface(InterfaceType.INVENTORY, MY_OFFERS_INVENTORY_WIDGET);
        player.getPacketSender().sendClientScript(149, "IviiiIsssss", MY_OFFERS_INVENTORY_WIDGET << 16, 93, 4, 7, 0, -1,
                "Select", "", "", "", "");
        player.getPacketSender().sendAccessMask(MY_OFFERS_INVENTORY_WIDGET, 0, 0, 27, 1086);
    }

    private void updateMyOffers() {
        for (int index = 0; index < MAX_MY_OFFERS; index++) {
            updateMyOffer(index, index >= myOffers.size() ? null : myOffers.get(index));
        }
    }

    private void updateMyOffer(int index, TradePostOffer offer) {
        String price = "Price: <col=ffffff>" + (offer == null ? "-" : formatPrice(offer.getPricePerItem()) + " ea");
        String totalPrice = offer == null ? ""
                : "<col=999999>=" + formatPrice((long) offer.getPricePerItem() *
                offer.getItem().getAmount()) + " total";
        int titleWidgetId = 27 + (15 * index);
        int priceWidgetId = 37 + (15 * index);
        int totalPriceWidgetId = 38 + (15 * index);
        int adjustButtonWidgetId = 29 + (15 * index);
        int resetButtonWidgetId = 33 + (15 * index);
        int containerWidgetId = 39 + (15 * index);
        int itemContainerId = 1000 + index;

        player.getPacketSender().sendClientScript(
                149, "IviiiIsssss",
                MY_OFFERS_WIDGET << 16 | containerWidgetId, itemContainerId,
                4, 7, 1, -1, "", "", "", "", ""
        );
        player.getPacketSender().sendItems(
                MY_OFFERS_WIDGET,
                containerWidgetId,
                itemContainerId,
                offer == null ? null : offer.getItem()
        );
        player.getPacketSender().sendString(
                MY_OFFERS_WIDGET,
                titleWidgetId, offer == null ? "Empty Slot" : offer.getItem().getDef().name
        );
        player.getPacketSender().sendString(MY_OFFERS_WIDGET, priceWidgetId, price);
        player.getPacketSender().sendString(MY_OFFERS_WIDGET, totalPriceWidgetId, totalPrice);
        player.getPacketSender().sendClientScript(69, "ii", offer != null ? 0 : 1, MY_OFFERS_WIDGET << 16 | adjustButtonWidgetId);
        player.getPacketSender().sendClientScript(69, "ii", offer != null ? 0 : 1, MY_OFFERS_WIDGET << 16 | resetButtonWidgetId);
    }

    private List<TradePostOffer> findViewOffers() {
        return World.getPlayerStream()
                .flatMap(player -> player.getTradePost().myOffers.stream())
                .filter(offer -> {
                    if (searchText == null) {
                        return true;
                    }

                    return offer.getItem().getDef().name.toLowerCase().contains(searchText);
                }).sorted((offerA, offerB) -> {
                    switch (sort) {
                        case PRICE_DESCENDING:
                            return offerB.getPricePerItem() - offerA.getPricePerItem();
                        case PRICE_ASCENDING:
                            return offerA.getPricePerItem() - offerB.getPricePerItem();
                        case AGE_DESCENDING:
                            return (int) (offerA.getTimestamp() - offerB.getTimestamp());
                        case AGE_ASCENDING:
                        default:
                            return (int) (offerB.getTimestamp() - offerA.getTimestamp());
                    }
                }).limit(MAX_VIEW_OFFERS)
                .collect(Collectors.toList());
    }

    private void sortByPrice() {
        if (sort == TradePostSort.PRICE_ASCENDING) {
            sort = TradePostSort.PRICE_DESCENDING;
        } else {
            sort = TradePostSort.PRICE_ASCENDING;
        }
        updateViewOffers();
    }

    private void sortByAge() {
        if (sort == TradePostSort.AGE_ASCENDING) {
            sort = TradePostSort.AGE_DESCENDING;
        } else {
            sort = TradePostSort.AGE_ASCENDING;
        }
        updateViewOffers();
    }

    private void promptSearch() {
        player.stringInput("Enter item name to search for:", searchText -> {
            this.searchText = searchText;
            updateSearch();
        });
    }

    private void resetSearch() {
        this.searchText = null;
        updateSearch();
    }

    private void updateSearch() {
        updateViewOffers();

        String search = searchText == null ? "-" : searchText;
        if (search.length() > 20) {
            search = search.substring(0, 18) + "...";
        }

        player.getPacketSender().sendString(VIEW_OFFERS_WIDGET, 32, search);
    }

    private void addFriend(int index) {
        if (index >= viewOffers.size()) {
            return;
        }

        String username = viewOffers.get(index).getUsername();
        player.sendMessage("Added '" + username + "' to your friends list.");
        CentralClient.sendSocialRequest(player.getUserId(), username, 1);
    }

    private void updateViewOffers() {
        viewOffers = findViewOffers();

        for (int index = 0; index < MAX_VIEW_OFFERS; index++) {
            hideViewOffer(index, true);
        }

        for (int index = 0; index < viewOffers.size(); index++) {
            updateViewOffer(index, viewOffers.get(index));
            hideViewOffer(index, false);
        }

        int scrollBarWidgetId = 204;
        int scrollContainerWidgetId = 43;
        player.getPacketSender().sendClientScript(
                30, "ii",
                VIEW_OFFERS_WIDGET << 16 | scrollBarWidgetId,
                VIEW_OFFERS_WIDGET << 16 | scrollContainerWidgetId
        );

    }

    private void hideViewOffer(int index, boolean hidden) {
        player.getPacketSender().sendClientScript(69, "ii", hidden ? 1 : 0, VIEW_OFFERS_WIDGET << 16 | (44 + (8 * index)));
    }

    private void updateViewOffer(int index, TradePostOffer offer) {
        String price = formatPrice(offer.getPricePerItem()) + " ea";
        String totalPrice = "=" + formatPrice((long) offer.getPricePerItem() *
                offer.getItem().getAmount()) + " total";

        int containerWidgetId = 46 + (index * 8);
        int itemContainerId = 1100 + index;
        int priceWidgetId = 47 + (index * 8);
        int totalPriceWidgetId = 48 + (index * 8);
        int usernameWidgetId = 49 + (index * 8);
        int ageWidgetId = 50 + (index * 8);

        player.getPacketSender().sendClientScript(
                149, "IviiiIsssss",
                VIEW_OFFERS_WIDGET << 16 | containerWidgetId, itemContainerId,
                4, 7, 1, -1, "", "", "", "", ""
        );
        player.getPacketSender().sendItems(VIEW_OFFERS_WIDGET, containerWidgetId, itemContainerId, offer.getItem());
        player.getPacketSender().sendString(VIEW_OFFERS_WIDGET, priceWidgetId, price);
        player.getPacketSender().sendString(VIEW_OFFERS_WIDGET, totalPriceWidgetId, totalPrice);
        player.getPacketSender().sendString(VIEW_OFFERS_WIDGET, usernameWidgetId, offer.getUsername());
        player.getPacketSender().sendString(VIEW_OFFERS_WIDGET, ageWidgetId, formatAge(offer.getTimestamp()));
    }

    private String formatPrice(long price) {
        if (price > 9_999_999) {
            return NumberUtils.formatNumber(price / 1000_000) + "M";
        } else if (price > 99_999) {
            return NumberUtils.formatNumber(price / 1000) + "K";
        }

        return NumberUtils.formatNumber(price);
    }

    private String formatAge(long timestamp) {
        long elapsed = System.currentTimeMillis() - timestamp;
        long days = TimeUnit.MILLISECONDS.toDays(elapsed);
        long hours = TimeUnit.MILLISECONDS.toHours(elapsed);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(elapsed);

        return days > 0 ? days + "d"
                : hours > 0 ? hours + "h"
                : minutes + "min";
    }

    static {
        InterfaceHandler.register(MY_OFFERS_INVENTORY_WIDGET, handler -> {
            handler.actions[0] = new InterfaceAction() {
                public void handleClick(Player player, int option, int slot, int itemId) {
                    player.getTradePost().promptCreateOffer(itemId);
                }
            };
        });

        InterfaceHandler.register(MY_OFFERS_WIDGET, handler -> {
            handler.actions[19] = (SimpleAction) player -> {
                player.getTradePost().openViewOffers();
            };
            for (int i = 0; i < MAX_MY_OFFERS; i++) {
                final int index = i;
                handler.actions[31 + (index * 15)] = (SimpleAction) player -> {
                    player.getTradePost().promptAdjustOffer(index);
                };
                handler.actions[35 + (index * 15)] = (SimpleAction) player -> {
                    player.getTradePost().resetOffer(index);
                };
            }
        });

        InterfaceHandler.register(VIEW_OFFERS_WIDGET, handler -> {
            handler.actions[42] = (SimpleAction) player -> {
                player.getTradePost().openMyOffers();
            };
            handler.actions[17] = (SimpleAction) player -> {
                player.getTradePost().sortByPrice();
            };
            handler.actions[20] = (SimpleAction) player -> {
                player.getTradePost().sortByAge();
            };
            handler.actions[26] = (SimpleAction) player -> {
                player.getTradePost().promptSearch();
            };
            handler.actions[30] = (SimpleAction) player -> {
                player.getTradePost().resetSearch();
            };
            for (int i = 0; i < MAX_VIEW_OFFERS; i++) {
                final int index = i;
                handler.actions[51 + (index * 8)] = (SimpleAction) player -> {
                    player.getTradePost().addFriend(index);
                };
            }
        });
    }


}