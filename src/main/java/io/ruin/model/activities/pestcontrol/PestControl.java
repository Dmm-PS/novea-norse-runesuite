package io.ruin.model.activities.pestcontrol;

import io.ruin.model.entity.npc.NPC;
import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.InterfaceAction;
import io.ruin.model.inter.InterfaceHandler;
import io.ruin.model.inter.InterfaceType;
import io.ruin.model.inter.actions.SimpleAction;
import io.ruin.model.inter.dialogue.NPCDialogue;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.item.Item;
import io.ruin.model.map.object.actions.ObjectAction;

import java.util.Arrays;

/**
 * The manager for all things relevant to Pest Control.
 * @author Heaven
 */
public class PestControl {

	/**
	 * The interface id to display rewards obtainable from Pest Control games.
	 */
	private static final int REWARDS_INTERFACE = 267;

	/**
	 * The {@link NPC} id for the Squire within the battlegrounds.
	 */
	public static final int SQUIRE_ID = 2949;

	/**
	 * The novice {@link PestControlBoat} instance.
	 */
	public static final PestControlBoat NOVICE_LANDER = new PestControlBoat(PestControlGameSettings.NOVICE);

	/**
	 * The intermediate {@link PestControlBoat} instance.
	 */
	private static final PestControlBoat INTERMEDIATE_LANDER = new PestControlBoat(PestControlGameSettings.INTERMEDIATE);

	/**
	 * The veteran {@link PestControlBoat} instance.
	 */
	private static final PestControlBoat VETERAN_LANDER = new PestControlBoat(PestControlGameSettings.VETERAN);

	static {
		NPCAction.register(1755, 3, (player, npc) -> displayRewardsShop(player));
		ObjectAction.register(14315, 1, NOVICE_LANDER::join);
		ObjectAction.register(25631, 1, INTERMEDIATE_LANDER::join);
		ObjectAction.register(25632, 1, VETERAN_LANDER::join);
		ObjectAction.register(14314, 1, (p, __) -> {
			NOVICE_LANDER.leave(p);
			p.getMovement().teleport(NOVICE_LANDER.settings().exitTile());
		});
		ObjectAction.register(25629, 1, (p, __) -> {
			INTERMEDIATE_LANDER.leave(p);
			p.getMovement().teleport(INTERMEDIATE_LANDER.settings().exitTile());
		});
		ObjectAction.register(25630, 1, (p, __) -> {
			VETERAN_LANDER.leave(p);
			p.getMovement().teleport(VETERAN_LANDER.settings().exitTile());
		});
		NPCAction.register(2949, 1, (player, squire) -> {
			if (player.pestGame == null)
				return;

			player.dialogue(
					new NPCDialogue(squire.getId(), "Be quick, we're under attack!"),
					new OptionsDialogue("Select an Option", new Option("I'd like to leave.", () -> player.pestGame.leave(player)), new Option("Nevermind."))
			);
		});
		NPCAction.register(2949, 3, (player, squire) -> {
			if (player.pestGame == null)
				return;

			player.pestGame.leave(player);
		});
		InterfaceHandler.register(REWARDS_INTERFACE, h -> {
			for (PestControlRewards r : PestControlRewards.VALUES) {
				h.actions[r.widgetId()] = (SimpleAction) player -> selectShopItem(player, r);
			}

			h.actions[146] = (SimpleAction) PestControl::confirmShopPurchase;
		});
	}

	/**
	 * Opens the rewards interface and displays all items / perks buyable with Pest Control points.
	 * @param player
	 */
	private static void displayRewardsShop(Player player) {
		player.selectedWidgetId = 0;
		player.getPacketSender().sendString(REWARDS_INTERFACE, 149, "");
		player.getPacketSender().sendString(REWARDS_INTERFACE, 150, "Points: "+ player.pestPoints);
		player.openInterface(InterfaceType.MAIN, REWARDS_INTERFACE);
	}

	/**
	 * Handles the selection of a shop item within the Void Knights' Reward Options
	 * @param player
	 */
	private static void selectShopItem(Player player, PestControlRewards reward) {
		player.selectedWidgetId = reward.widgetId();
		player.getPacketSender().sendString(REWARDS_INTERFACE, 149, reward.displayName());
	}

	/**
	 * Attempts to purchase the selected item if the player has enough points to do so. If completed, we deduct the cost and reset
	 * their selection.
	 * @param player
	 */
	private static void confirmShopPurchase(Player player) {
		if (player.selectedWidgetId != 0) {
			if (player.getInventory().isFull()) {
				player.sendMessage("You do not have enough space in your inventory.");
				return;
			}

			PestControlRewards selected = Arrays.stream(PestControlRewards.VALUES).filter(i -> player.selectedWidgetId == i.widgetId()).findAny().orElse(null);
			if (selected != null) {
				if (player.pestPoints < selected.cost()) {
					player.sendMessage("You do not have enough Pest Points to purchase this "+ selected.displayName() +".");
					return;
				}

				player.pestPoints -= selected.cost();
				player.getInventory().add(new Item(selected.itemId()));
				player.selectedWidgetId = 0;
				player.getPacketSender().sendString(REWARDS_INTERFACE, 149, "");
				player.getPacketSender().sendString(REWARDS_INTERFACE, 150, "Points: "+ player.pestPoints);
			}
		}
	}
}
