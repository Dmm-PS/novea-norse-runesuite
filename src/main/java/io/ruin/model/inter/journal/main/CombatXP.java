package io.ruin.model.inter.journal.main;

import io.ruin.cache.Color;
import io.ruin.model.World;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.journal.JournalEntry;
import io.ruin.model.inter.utils.Option;

public class CombatXP extends JournalEntry {

    @Override
    public void send(Player player) {
        send(player, "Combat XP", player.combatXpRate == 0 ? "Locked" : ("x" + player.combatXpRate), Color.GREEN);
    }

    @Override
    public void select(Player player) {
        if (World.isEco()) {
            player.dialogue(
                new OptionsDialogue("Select a combat experience rate.",
                        new Option("x1", () -> set(player, 1)),
                        new Option("x10", () -> set(player, 10)),
                        new Option("x20", () -> set(player, 20)),
                        new Option("x100", () -> set(player, 100)),
                        new Option("Lock", () -> set(player, 0))
                )
        );
        } else {
            player.dialogue(
                    new OptionsDialogue("Select a combat experience rate.",
                            new Option("x1", () -> set(player, 1)),
                            new Option("x15", () -> set(player, 15)),
                            new Option("x150", () -> set(player, 150)),
                            new Option("x250", () -> set(player, 250)),
                            new Option("Lock", () -> set(player, 0))
                    )
            );
        }
    }

    private void set(Player player, int rate) {
/*        if (player.getGameMode().isIronMan() && rate > 20) {
            Dialogue.send(player, "Ironman accounts can only set their combat XP rate to 20x or lower.");
            return;
        }*/
        player.combatXpRate = rate;
        player.sendMessage("Your combat experience is now: " + Color.GREEN.wrap(player.combatXpRate == 0 ? "Locked" : ("x" + player.combatXpRate)));
        send(player);
    }

}