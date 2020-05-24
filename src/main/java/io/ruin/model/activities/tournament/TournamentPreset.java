package io.ruin.model.activities.tournament;

import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.journal.presets.Preset;
import io.ruin.model.inter.utils.Config;
import io.ruin.model.item.Item;
import io.ruin.model.item.ItemContainer;
import io.ruin.model.item.actions.impl.storage.RunePouch;
import io.ruin.model.skills.magic.SpellBook;
import io.ruin.model.stat.StatType;

import java.util.function.Consumer;

public enum TournamentPreset {

    DHAROK(
            false,
            99, 99, 99, 99, 99, 99, 99,
            SpellBook.LUNAR,
            new Item[]{
                    new Item(Preset.SANFEW_SERUM),
                    new Item(Preset.SANFEW_SERUM),
                    new Item(Preset.SHARK),
                    new Item(Preset.SHARK),
                    new Item(Preset.COOKED_KARAMBWAN),
                    new Item(Preset.COOKED_KARAMBWAN),
                    new Item(Preset.COOKED_KARAMBWAN),
                    new Item(Preset.SHARK),
                    new Item(Preset.SHARK),
                    new Item(Preset.COOKED_KARAMBWAN),
                    new Item(Preset.SHARK),
                    new Item(Preset.SHARK),
                    new Item(Preset.SARADOMIN_BREW_4),
                    new Item(Preset.COOKED_KARAMBWAN),
                    new Item(Preset.SHARK),
                    new Item(Preset.SHARK),
                    new Item(Preset.GUTHIX_REST),
                    new Item(Preset.GUTHIX_REST),
                    new Item(Preset.SHARK),
                    new Item(Preset.SHARK),
                    new Item(Preset.GRANITE_MAUL),
                    new Item(Preset.DHAROK_GREATAXE),
                    new Item(Preset.PINEAPPLE_PIZZA),
                    new Item(Preset.PINEAPPLE_PIZZA),
                    new Item(Preset.SUPER_COMBAT_POTION),
                    new Item(Preset.DEATH_RUNE, 250),
                    new Item(Preset.EARTH_RUNE, 1000),
                    new Item(Preset.ASTRAL_RUNE, 500)
            },
            new Item[]{
                    new Item(Preset.DHAROK_HELMET),
                    new Item(Preset.INFERNAL_CAPE),
                    new Item(Preset.AMULET_OF_FURY),
                    new Item(Preset.ABYSSAL_TENTACLE),
                    new Item(Preset.DHAROK_BODY),
                    new Item(Preset.DRAGON_DEFENDER),
                    null,
                    new Item(Preset.DHAROK_LEGS),
                    null,
                    new Item(Preset.BARROWS_GLOVES),
                    new Item(Preset.DRAGON_BOOTS),
                    null,
                    new Item(Preset.RING_OF_RECOIL),
                    null
            }
    ),
    PURE_NH(
            true,
            75, 99, 1, 99, 52, 99, 99,
            SpellBook.ANCIENT,
            new Item[]{
                    new Item(Preset.ARMADYL_CROSSBOW),
                    new Item(Preset.AVAS_ASSEMBLER),
                    new Item(Preset.RANGING_POTION_4),
                    new Item(Preset.SUPER_COMBAT_POTION),
                    new Item(Preset.BLACK_DRAGONHIDE_CHAPS),
                    new Item(Preset.DRAGON_SCIMITAR),
                    new Item(Preset.DRAGON_DAGGER),
                    new Item(Preset.SARADOMIN_BREW_4),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.COOKED_KARAMBWAN),
                    new Item(Preset.SANFEW_SERUM),
                    new Item(Preset.SANFEW_SERUM),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.COOKED_KARAMBWAN),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.BLOOD_RUNE, 800),
                    new Item(Preset.DEATH_RUNE, 1600),
                    new Item(Preset.WATER_RUNE, 2400)
            },
            new Item[]{
                    new Item(Preset.ELDER_CHAOS_HOOD),
                    new Item(Preset.IMBUED_ZAMORAK_CAPE),
                    new Item(Preset.AMULET_OF_FURY),
                    new Item(Preset.TOXIC_STAFF_OF_THE_DEAD),
                    new Item(Preset.ELDER_CHAOS_TOP),
                    new Item(Preset.MAGES_BOOK),
                    null,
                    new Item(Preset.ELDER_CHAOS_ROBE),
                    null,
                    new Item(Preset.MITHRIL_GLOVES),
                    new Item(Preset.WIZARD_BOOTS),
                    null,
                    new Item(Preset.BERSERKER_RING_IMBUE),
                    new Item(Preset.DRAGON_BOLT, 100)
            }
    ),
    MAIN_NO_ARMOUR(
            false,
            99, 99, 99, 99, 99, 99, 99,
            SpellBook.LUNAR,
            new Item[]{
                    new Item(Preset.SANFEW_SERUM),
                    new Item(Preset.SANFEW_SERUM),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.COOKED_KARAMBWAN),
                    new Item(Preset.COOKED_KARAMBWAN),
                    new Item(Preset.COOKED_KARAMBWAN),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.COOKED_KARAMBWAN),
                    new Item(Preset.SARADOMIN_BREW_4),
                    new Item(Preset.SARADOMIN_BREW_4),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.COOKED_KARAMBWAN),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.GRANITE_MAUL),
                    new Item(Preset.BANDOS_GODSWORD),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.SUPER_COMBAT_POTION),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.DEATH_RUNE, 800),
                    new Item(Preset.EARTH_RUNE, 400),
                    new Item(Preset.ASTRAL_RUNE, 2000)
            },
            new Item[]{
                    new Item(Preset.HELM_OF_NEITIZNOT),
                    new Item(Preset.INFERNAL_CAPE),
                    new Item(Preset.AMULET_OF_FURY),
                    new Item(Preset.ABYSSAL_WHIP),
                    new Item(Preset.MONK_ROBE_TOP),
                    new Item(Preset.DRAGON_DEFENDER),
                    null,
                    new Item(Preset.MONK_ROBE_BOTTOM),
                    null,
                    new Item(Preset.BARROWS_GLOVES),
                    new Item(Preset.DRAGON_BOOTS),
                    null,
                    new Item(Preset.RING_OF_RECOIL),
                    null
            }
    ),
    PURE_RANGE_GRANITE_MAUL(
            false,
            60, 99, 1, 99, 52, 99, 99,
            SpellBook.MODERN,
            new Item[]{
                    new Item(Preset.SHARK),
                    new Item(Preset.SHARK),
                    new Item(Preset.SHARK),
                    new Item(Preset.SANFEW_SERUM),
                    new Item(Preset.SHARK),
                    new Item(Preset.SHARK),
                    new Item(Preset.SHARK),
                    new Item(Preset.SANFEW_SERUM),
                    new Item(Preset.COOKED_KARAMBWAN),
                    new Item(Preset.COOKED_KARAMBWAN),
                    new Item(Preset.COOKED_KARAMBWAN),
                    new Item(Preset.SARADOMIN_BREW_4),
                    new Item(Preset.SHARK),
                    new Item(Preset.COOKED_KARAMBWAN),
                    new Item(Preset.SHARK),
                    new Item(Preset.RANGING_POTION_4),
                    new Item(Preset.SHARK),
                    new Item(Preset.COOKED_KARAMBWAN),
                    new Item(Preset.SHARK),
                    new Item(Preset.SUPER_COMBAT_POTION),
                    new Item(Preset.GRANITE_MAUL),
                    new Item(Preset.BARRELCHEST_ANCHOR),
                    new Item(Preset.SHARK),
                    new Item(Preset.RING_OF_RECOIL),
                    new Item(Preset.SHARK),
                    new Item(Preset.SHARK),
                    new Item(Preset.SHARK),
                    new Item(Preset.SHARK)
            },
            new Item[]{
                    new Item(Preset.ZAMORAK_HALO),
                    new Item(Preset.INFERNAL_CAPE),
                    new Item(Preset.AMULET_OF_FURY),
                    new Item(Preset.MAGIC_SHORTBOW_IMBUE),
                    new Item(Preset.MONK_ROBE_TOP),
                    null,
                    null,
                    new Item(Preset.BLACK_DRAGONHIDE_CHAPS),
                    null,
                    new Item(Preset.MITHRIL_GLOVES),
                    new Item(Preset.CLIMBING_BOOTS),
                    null,
                    new Item(Preset.RING_OF_RECOIL),
                    new Item(Preset.RUNE_ARROW, 500)
            }
    ),
    MAIN_WELFARE_NH(
            true,
            99, 99, 99, 99, 99, 99, 99,
            SpellBook.ANCIENT,
            new Item[]{
                    new Item(Preset.RUNE_CROSSBOW),
                    new Item(Preset.BLACK_DRAGONHIDE_BODY),
                    new Item(Preset.ABYSSAL_TENTACLE),
                    new Item(Preset.RANGING_POTION_4),
                    new Item(Preset.AVAS_ASSEMBLER),
                    new Item(Preset.RUNE_PLATELEGS),
                    new Item(Preset.DRAGON_DEFENDER),
                    new Item(Preset.SUPER_COMBAT_POTION),
                    new Item(Preset.SHARK),
                    new Item(Preset.SHARK),
                    new Item(Preset.DRAGON_DAGGER),
                    new Item(Preset.SARADOMIN_BREW_4),
                    new Item(Preset.SHARK),
                    new Item(Preset.SHARK),
                    new Item(Preset.SHARK),
                    new Item(Preset.SARADOMIN_BREW_4),
                    new Item(Preset.SHARK),
                    new Item(Preset.SHARK),
                    new Item(Preset.SHARK),
                    new Item(Preset.SARADOMIN_BREW_4),
                    new Item(Preset.SHARK),
                    new Item(Preset.SANFEW_SERUM),
                    new Item(Preset.SANFEW_SERUM),
                    new Item(Preset.SARADOMIN_BREW_4),
                    new Item(Preset.SHARK),
                    new Item(Preset.DEATH_RUNE, 5000),
                    new Item(Preset.BLOOD_RUNE, 5000),
                    new Item(Preset.WATER_RUNE, 10000)
            },
            new Item[]{
                    new Item(Preset.HELM_OF_NEITIZNOT),
                    new Item(Preset.IMBUED_ZAMORAK_CAPE),
                    new Item(Preset.AMULET_OF_GLORY_6),
                    new Item(Preset.ANCIENT_STAFF),
                    new Item(Preset.MYSTIC_ROBE_TOP),
                    new Item(Preset.SPIRIT_SHIELD),
                    null,
                    new Item(Preset.MYSTIC_ROBE_BOTTOM),
                    null,
                    new Item(Preset.BARROWS_GLOVES),
                    new Item(Preset.CLIMBING_BOOTS),
                    null,
                    new Item(Preset.SEERS_RING_IMBUE),
                    new Item(Preset.DRAGON_BOLT, 100)
            }
    ),
    MYSTIC_BARROWS(
            false,
            99, 99, 99, 99, 99, 99, 99,
            SpellBook.ANCIENT,
            new Item[]{
                    new Item(Preset.TORAG_PLATEBODY),
                    new Item(Preset.DRAGON_DEFENDER),
                    new Item(Preset.INFERNAL_CAPE),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.VERAC_PLATESKIRT),
                    new Item(Preset.ABYSSAL_TENTACLE),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.KARIL_LEATHERTOP),
                    new Item(Preset.DRAGON_DAGGER),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.SARADOMIN_BREW_4),
                    new Item(Preset.SARADOMIN_BREW_4),
                    new Item(Preset.SARADOMIN_BREW_4),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.SANFEW_SERUM),
                    new Item(Preset.SANFEW_SERUM),
                    new Item(Preset.SUPER_COMBAT_POTION),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.DEATH_RUNE, 5000),
                    new Item(Preset.BLOOD_RUNE, 5000),
                    new Item(Preset.WATER_RUNE, 10000)
            },
            new Item[]{
                    new Item(Preset.HELM_OF_NEITIZNOT),
                    new Item(Preset.IMBUED_SARADOMIN_CAPE),
                    new Item(Preset.AMULET_OF_FURY),
                    new Item(Preset.ANCIENT_STAFF),
                    new Item(Preset.MYSTIC_ROBE_TOP),
                    new Item(Preset.BLESSED_SPIRIT_SHIELD),
                    null,
                    new Item(Preset.MYSTIC_ROBE_BOTTOM),
                    null,
                    new Item(Preset.BARROWS_GLOVES),
                    new Item(Preset.DRAGON_BOOTS),
                    null,
                    new Item(Preset.SEERS_RING_IMBUE),
                    null
            }
    ),
    MAIN_NH(
            true,
            99, 99, 99, 99, 99, 99, 99,
            SpellBook.ANCIENT,
            new Item[]{
                    new Item(Preset.ARMADYL_CROSSBOW),
                    new Item(Preset.KARIL_LEATHERTOP),
                    new Item(Preset.DRAGON_DEFENDER),
                    new Item(Preset.SUPER_COMBAT_POTION),
                    new Item(Preset.AVAS_ASSEMBLER),
                    new Item(Preset.VERAC_PLATESKIRT),
                    new Item(Preset.ABYSSAL_TENTACLE),
                    new Item(Preset.RANGING_POTION_4),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.SARADOMIN_BREW_4),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.SARADOMIN_BREW_4),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.SARADOMIN_BREW_4),
                    new Item(Preset.ARMADYL_GODSWORD),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.SANFEW_SERUM),
                    new Item(Preset.SANFEW_SERUM),
                    new Item(Preset.DARK_CRAB),
                    new Item(Preset.DEATH_RUNE, 5000),
                    new Item(Preset.BLOOD_RUNE, 5000),
                    new Item(Preset.WATER_RUNE, 10000)
            },
            new Item[]{
                    new Item(Preset.SERPENTINE_HELM),
                    new Item(Preset.IMBUED_SARADOMIN_CAPE),
                    new Item(Preset.AMULET_OF_FURY),
                    new Item(Preset.TOXIC_STAFF_OF_THE_DEAD),
                    new Item(Preset.AHRIM_ROBETOP),
                    new Item(Preset.ARCANE_SPIRIT_SHIELD),
                    null,
                    new Item(Preset.AHRIM_ROBESKIRT),
                    null,
                    new Item(Preset.BARROWS_GLOVES),
                    new Item(Preset.ETERNAL_BOOTS),
                    null,
                    new Item(Preset.SEERS_RING_IMBUE),
                    new Item(Preset.DRAGON_BOLT, 100)
            }
    ),
    DMM_TRIBRID(
            true,
            99, 99, 99, 99, 99, 99, 99,
            SpellBook.ANCIENT,
            new Item[]{
                    new Item(Preset.ARMADYL_CROSSBOW),
                    new Item(11828),
                    new Item(22613),
                    new Item(Preset.COOKED_KARAMBWAN),
                    new Item(11283),
                    new Item(Preset.VERAC_PLATESKIRT),
                    new Item(Preset.DRAGON_DEFENDER),
                    new Item(Preset.COOKED_KARAMBWAN),
                    new Item(Preset.DHAROK_BODY),
                    new Item(Preset.SHARK),
                    new Item(Preset.SHARK),
                    new Item(Preset.SHARK),
                    new Item(Preset.SHARK),
                    new Item(Preset.SHARK),
                    new Item(Preset.SHARK),
                    new Item(Preset.SHARK),
                    new Item(Preset.SHARK),
                    new Item(Preset.SARADOMIN_BREW_4),
                    new Item(Preset.SHARK),
                    new Item(Preset.RANGING_POTION_4),
                    new Item(Preset.SANFEW_SERUM),
                    new Item(Preset.SUPER_RESTORE_POTION_4),
                    new Item(Preset.SHARK),
                    new Item(Preset.SUPER_COMBAT_POTION),
                    new Item(566, 1000),
                    new Item(Preset.SHARK),
                    new Item(Preset.SHARK),
                    new Item(RunePouch.RUNE_POUCH)
            },
            new Item[]{
                    new Item(4745),
                    new Item(Preset.IMBUED_SARADOMIN_CAPE),
                    new Item(Preset.AMULET_OF_FURY),
                    new Item(22647),
                    new Item(Preset.AHRIM_ROBETOP),
                    new Item(Preset.MAGES_BOOK),
                    null,
                    new Item(Preset.AHRIM_ROBESKIRT),
                    null,
                    new Item(Preset.BARROWS_GLOVES),
                    new Item(6920),
                    null,
                    new Item(Preset.SEERS_RING_IMBUE),
                    new Item(21948, 100)
            },
            (player) -> {
                if (Config.RIGOUR_UNLOCK.get(player) == 0) {
                    player.tournamentRigour = true;
                    Config.RIGOUR_UNLOCK.set(player, 1);
                }
                if (Config.AUGURY_UNLOCK.get(player) == 0) {
                    player.tournamentAugury = true;
                    Config.AUGURY_UNLOCK.set(player, 1);
                }
                if (Config.PRESERVE_UNLOCK.get(player) == 0) {
                    player.tournamentPreserve = true;
                    Config.PRESERVE_UNLOCK.set(player, 1);
                }
                if (!player.getRunePouch().isEmpty()) {
                    player.cachedRunePouchTypes = Config.RUNE_POUCH_TYPES.get(player);
                    player.cachedRunePouchAmounts = Config.RUNE_POUCH_AMOUNTS.get(player);
                }
                player.tournamentPouch = true;
                player.getRunePouch().set(0, new Item(Preset.WATER_RUNE, 10_000));
                player.getRunePouch().set(1, new Item(Preset.DEATH_RUNE, 8_000));
                player.getRunePouch().set(2, new Item(Preset.BLOOD_RUNE, 4_000));
            }
    ),
    DMM_RANGE_MELEE(
            true,
            99, 99, 99, 99, 99, 99, 99,
            SpellBook.LUNAR,
            new Item[]{
                    new Item(22613),
                    new Item(Preset.DHAROK_BODY),
                    new Item(Preset.SHARK),
                    new Item(Preset.COOKED_KARAMBWAN),
                    new Item(Preset.DRAGON_DEFENDER),
                    new Item(Preset.DHAROK_LEGS),
                    new Item(Preset.SHARK),
                    new Item(Preset.COOKED_KARAMBWAN),
                    new Item(Preset.SHARK),
                    new Item(Preset.SHARK),
                    new Item(Preset.SHARK),
                    new Item(Preset.SHARK),
                    new Item(Preset.SHARK),
                    new Item(Preset.SHARK),
                    new Item(Preset.SHARK),
                    new Item(Preset.SHARK),
                    new Item(Preset.SHARK),
                    new Item(Preset.SARADOMIN_BREW_4),
                    new Item(Preset.SHARK),
                    new Item(Preset.RANGING_POTION_4),
                    new Item(Preset.SANFEW_SERUM),
                    new Item(Preset.SUPER_RESTORE_POTION_4),
                    new Item(Preset.SUPER_COMBAT_POTION),
                    new Item(RunePouch.RUNE_POUCH),
                    null,
                    null,
                    null,
                    null
            },
            new Item[]{
                    new Item(4745),
                    new Item(Preset.FIRE_CAPE),
                    new Item(Preset.AMULET_OF_FURY),
                    new Item(Preset.ARMADYL_CROSSBOW),
                    new Item(Preset.KARIL_LEATHERTOP),
                    new Item(11283),
                    null,
                    new Item(4738),
                    null,
                    new Item(Preset.BARROWS_GLOVES),
                    new Item(Preset.DRAGON_BOOTS),
                    null,
                    new Item(11771),
                    new Item(21948, 100)
            },
            (player) -> {
                if (Config.RIGOUR_UNLOCK.get(player) == 0) {
                    player.tournamentRigour = true;
                    Config.RIGOUR_UNLOCK.set(player, 1);
                }
                if (Config.AUGURY_UNLOCK.get(player) == 0) {
                    player.tournamentAugury = true;
                    Config.AUGURY_UNLOCK.set(player, 1);
                }
                if (Config.PRESERVE_UNLOCK.get(player) == 0) {
                    player.tournamentPreserve = true;
                    Config.PRESERVE_UNLOCK.set(player, 1);
                }
                if (!player.getRunePouch().isEmpty()) {
                    player.cachedRunePouchTypes = Config.RUNE_POUCH_TYPES.get(player);
                    player.cachedRunePouchAmounts = Config.RUNE_POUCH_AMOUNTS.get(player);
                }
                player.tournamentPouch = true;
                player.getRunePouch().set(0, new Item(Preset.EARTH_RUNE, 10_000));
                player.getRunePouch().set(1, new Item(Preset.DEATH_RUNE, 8_000));
                player.getRunePouch().set(2, new Item(Preset.ASTRAL_RUNE, 4_000));
            }
    );

    public final boolean overheadsAllowed;
    public final int attackLevel, strengthLevel, defenceLevel, rangedLevel, prayerLevel, magicLevel, hpLevel;
    public final SpellBook spellbook;
    public final Item[] invItems, equipItems;
    public Consumer<Player> onSelect;

    TournamentPreset(boolean overheadsAllowed,
                     int attackLevel, int strengthLevel, int defenceLevel, int rangedLevel, int prayerLevel, int magicLevel, int hpLevel,
                     SpellBook spellbook,
                     Item[] invItems, Item[] equipItems) {
        this.overheadsAllowed = overheadsAllowed;
        this.attackLevel = attackLevel;
        this.strengthLevel = strengthLevel;
        this.defenceLevel = defenceLevel;
        this.rangedLevel = rangedLevel;
        this.prayerLevel = prayerLevel;
        this.magicLevel = magicLevel;
        this.hpLevel = hpLevel;
        this.spellbook = spellbook;
        this.invItems = invItems;
        this.equipItems = equipItems;
        this.onSelect = null;
    }

    TournamentPreset(boolean overheadsAllowed,
                     int attackLevel, int strengthLevel, int defenceLevel, int rangedLevel, int prayerLevel, int magicLevel, int hpLevel,
                     SpellBook spellbook,
                     Item[] invItems, Item[] equipItems, Consumer<Player> fn) {
        this(overheadsAllowed, attackLevel, strengthLevel, defenceLevel, rangedLevel, prayerLevel, magicLevel, hpLevel, spellbook, invItems, equipItems);
        this.onSelect = fn;
    }

    public static void use(Player player, TournamentPreset preset) {
        if (player != null) {
            /**
             * Set inventory and equipment
             */
            setItems(player, preset.invItems, true);
            setItems(player, preset.equipItems, false);

            /**
             * Set combat levels
             */
            player.getStats().set(StatType.Attack, preset.attackLevel);
            player.getStats().set(StatType.Strength, preset.strengthLevel);
            player.getStats().set(StatType.Defence, preset.defenceLevel);
            player.getStats().set(StatType.Ranged, preset.rangedLevel);
            player.getStats().set(StatType.Prayer, preset.prayerLevel);
            player.getStats().set(StatType.Magic, preset.magicLevel);
            player.getStats().set(StatType.Hitpoints, preset.hpLevel);
            player.getCombat().updateLevel();

            /**
             * Set spellbook
             */
            preset.spellbook.setActive(player);

            /**
             * Set attribute for prayer check
             */
            player.tournamentPrayerDisabled = preset.overheadsAllowed;

            // IF there is a function listener available then invoke it on selection.
            if (preset.onSelect != null) {
                preset.onSelect.accept(player);
            }

            /*
             * Let players temp use raids prayers.
             */
            if (preset == MAIN_NH) {
                player.tempUseRaidPrayers = true;
            }
        }
    }

    private static void setItems(Player player, Item[] items, boolean inventory) {
        ItemContainer container = inventory ? player.getInventory() : player.getEquipment();
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                if (items[i].getId() == Preset.TOXIC_STAFF_OF_THE_DEAD || items[i].getId() == Preset.SERPENTINE_HELM)
                    items[i].setUniqueValue(5000);
                container.set(i, items[i].copy());
            }
        }
    }
}
