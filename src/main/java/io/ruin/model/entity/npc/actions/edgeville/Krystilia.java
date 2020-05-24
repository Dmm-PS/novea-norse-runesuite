package io.ruin.model.entity.npc.actions.edgeville;

import io.ruin.api.utils.Random;
import io.ruin.cache.Color;
import io.ruin.model.World;
import io.ruin.model.entity.npc.NPC;
import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.entity.player.Player;
import io.ruin.model.entity.player.PlayerCounter;
import io.ruin.model.entity.shared.listeners.SpawnListener;
import io.ruin.model.inter.dialogue.ActionDialogue;
import io.ruin.model.inter.dialogue.NPCDialogue;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.dialogue.PlayerDialogue;
import io.ruin.model.inter.utils.Config;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.item.containers.shop.Shop;
import io.ruin.model.skills.slayer.Slayer;
import io.ruin.model.skills.slayer.SlayerTask;
import io.ruin.model.skills.slayer.SlayerUnlock;

public class Krystilia {

    public static final int[] IDS = {7663, 8623};

    static {
        for(int ID : IDS) {
            SpawnListener.register(IDS, npc -> npc.skipReachCheck = p ->
                    p.equals(3109, 3515) || p.equals(3110, 3516)
            );
            NPCAction.register(ID, "Talk-to", (player, npc) -> {
                player.dialogue(
                        new NPCDialogue(npc, "Yeah? What do you want?"),
                        new OptionsDialogue(
                                player.slayerTask == null ?
                                        new Option("I want a Slayer assignment.", () -> {
                                            player.dialogue(
                                                    new PlayerDialogue("I want a Slayer assignment."),
                                                    new ActionDialogue(() -> assignment(player, npc))
                                            );
                                        })
                                        :
                                        new Option("Tell me about my Slayer assignment.", () -> {
                                            player.dialogue(
                                                    new PlayerDialogue("Tell me about my Slayer assignment."),
                                                    new ActionDialogue(() -> assignment(player, npc))
                                            );
                                        }),
                                new Option("Do you have anything to trade?", () -> {
                                    player.dialogue(
                                            new PlayerDialogue("Do you have anything to trade?"),
                                            new NPCDialogue(npc, "I have a wide variety of Slayer equipment for sale! Have a look..").lineHeight(28),
                                            new ActionDialogue(() -> Shop.trade(player, npc))
                                    );
                                }),
                                new Option("Have you any rewards for me?", () -> {
                                    player.dialogue(
                                            new PlayerDialogue("Have you any rewards for me?"),
                                            new NPCDialogue(npc, "I have quite a few rewards you can earn!<br>Take a look..").lineHeight(28),
                                            new ActionDialogue(() -> rewards(player))
                                    );
                                }),
                                new Option("Er... Nothing...", () -> player.dialogue(new PlayerDialogue("Er... Nothing...").animate(588)))
                        )
                );
            });
            NPCAction.register(ID, "Assignment", Krystilia::assignment);
            NPCAction.register(ID, "Trade", Shop::trade);
            NPCAction.register(ID, "Rewards", (player, npc) -> rewards(player));
        }
    }

    /**
     * Assignment
     */

    private static void assignment(Player player, NPC npc) {
        if(player.slayerTaskRemaining == -1) {
            requestAmount(player, npc);
            return;
        }
        if(Slayer.getTask(player) == null) {
            if (World.isEco() && PlayerCounter.SLAYER_TASKS_COMPLETED.get(player) == 0) { // force easy, non-wild task as first task
                Slayer.set(player, SlayerTask.Type.EASY, false);
                player.dialogue(new NPCDialogue(npc, "Your new task is to kill " + player.slayerTaskRemaining + " " + player.slayerTaskName + ".<br>Good luck!").lineHeight(28));
                return;
            }
            if(World.isPVP()) {
                player.dialogue(
                        new NPCDialogue(npc, "What kind of task would you like?"),
                        new OptionsDialogue(
                                new Option("I want a regular task.", () -> {
                                    player.dialogue(
                                            new PlayerDialogue("I want a " + Color.CRIMSON.wrap("regular") + " task."),
                                            new ActionDialogue(() -> assign(player, npc, SlayerTask.Type.HARD))
                                    );
                                }),
                                new Option("I want a boss task.", () -> {
                                    if (Config.LIKE_A_BOSS.get(player) == 1)
                                        player.dialogue(
                                                new PlayerDialogue("I want a " + Color.CRIMSON.wrap("boss") + " task."),
                                                new ActionDialogue(() -> assign(player, npc, SlayerTask.Type.BOSS))
                                        );
                                    else
                                        player.dialogue(
                                                new PlayerDialogue("I want a " + Color.CRIMSON.wrap("boss") + " task."),
                                                new NPCDialogue(npc, "You haven't unlocked the ability to receive boss tasks yet.")
                                        );
                                })
                        )
                );
            } else {
                player.dialogue(
                        new NPCDialogue(npc, "What kind of task would you like?"),
                        new OptionsDialogue(
                                new Option("I want an easy task.", () -> {
                                    player.dialogue(
                                            new PlayerDialogue("I want an " + Color.CRIMSON.wrap("easy") + " task."),
                                            new ActionDialogue(() -> assign(player, npc, SlayerTask.Type.EASY))
                                    );
                                }),
                                new Option("I want a medium task.", () -> {
                                    player.dialogue(
                                            new PlayerDialogue("I want a " + Color.CRIMSON.wrap("medium") + " task."),
                                            new ActionDialogue(() -> assign(player, npc, SlayerTask.Type.MEDIUM))
                                    );
                                }),
                                new Option("I want a hard task.", () -> {
                                    player.dialogue(
                                            new PlayerDialogue("I want a " + Color.CRIMSON.wrap("hard") + " task."),
                                            new ActionDialogue(() -> assign(player, npc, SlayerTask.Type.HARD))
                                    );
                                }),
                                new Option("I want a boss task.", () -> {
                                    if (Config.LIKE_A_BOSS.get(player) == 1)
                                        player.dialogue(
                                                new PlayerDialogue("I want a " + Color.CRIMSON.wrap("boss") + " task."),
                                                new ActionDialogue(() -> assign(player, npc, SlayerTask.Type.BOSS))
                                        );
                                    else
                                        player.dialogue(
                                                new PlayerDialogue("I want a " + Color.CRIMSON.wrap("boss") + " task."),
                                                new NPCDialogue(npc, "You haven't unlocked the ability to receive boss tasks yet.")
                                        );
                                })
                        )
                );
            }
            return;
        }
        player.dialogue(new NPCDialogue(npc, "You're currently assigned to kill " + player.slayerTaskName + "; only " + player.slayerTaskRemaining + " more to go.")/* Your reward point tally is " + tally + ".")*/.lineHeight(28));
    }

    private static void assign(Player player, NPC npc, SlayerTask.Type type) {
        if(World.isEco() && player.getCombat().getLevel() < type.minimumCombatLevel) {
            player.dialogue(
                    new NPCDialogue(npc, "You must have combat level of " + type.minimumCombatLevel + " or higher for this type of task.").lineHeight(28)
            );
        } else {
            player.dialogue(
                    new NPCDialogue(npc, "Do you prefer monsters that can be found in the wilderness?").lineHeight(28),
                    new OptionsDialogue(
                            assign("Yes, the more dangerous the better!", player, npc, type, true),
                            assign("No, I'd rather stay out of the wilderness.", player, npc, type, false),
                            assign("It doesn't matter, surprise me!", player, npc, type, !Random.rollDie(2, 1))
                    )
            );
        }
    }

    private static Option assign(String message, Player player, NPC npc, SlayerTask.Type type, Boolean preferWilderness) {
        return new Option(message, () -> {
           Slayer.set(player, type, preferWilderness);
           if(player.slayerTaskRemaining == -1) {
               player.dialogue(
                       new PlayerDialogue(message),
                       new ActionDialogue(() -> requestAmount(player, npc))
               );
           } else {
               player.dialogue(
                       new PlayerDialogue(message),
                       new NPCDialogue(npc, "Your new task is to kill " + player.slayerTaskRemaining + " " + player.slayerTaskName + ".<br>Good luck!").lineHeight(28)
               );
           }
        });
    }

    private static void requestAmount(Player player, NPC npc) {
        SlayerTask task = Slayer.getTask(player);
        if(task == null) { //literally should NEVER happen lol.
            Slayer.reset(player);
            player.dialogue(new NPCDialogue(npc, "Oh my!"));
        } else {
            player.dialogue(
                    new NPCDialogue(npc, "Your new task is to kill " + player.slayerTaskName + ". How many would you like to slay?").lineHeight(28),
                    new ActionDialogue(() -> {
                        player.closeDialogue();
                        player.integerInput("Enter amount: (" + task.min + "-" + task.max + ")", amt -> {
                            if(amt < task.min || amt > task.max) {
                                player.retryIntegerInput("Invalid amount, try again: (" + task.min + "-" + task.max + ")");
                                return;
                            }
                            player.slayerTaskRemaining = amt;
                            player.dialogue(new NPCDialogue(npc, "Your new task is to kill " + player.slayerTaskRemaining + " " + player.slayerTaskName + ".<br>Good luck!").lineHeight(28));
                        });
                    })
            );
        }
    }

    /**
     * Rewards
     */

    private static void rewards(Player player) {
        SlayerUnlock.openRewards(player);
    }

}