package io.ruin.model.activities.lastmanstanding;

import io.ruin.model.World;
import io.ruin.model.entity.player.Player;
import io.ruin.model.map.dynamic.DynamicMap;
import io.ruin.utility.TickDelay;

import java.util.LinkedList;
import java.util.List;

public class LastManStanding { //inter 333, 328

    private final DynamicMap LMSMap;

    private final List<Player> playersInLobby = new LinkedList<>();

    private final List<Player> playersFighting = new LinkedList<>();

    public LastManStanding() {
        LMSMap = createMap();
    }

    private TickDelay chestSpawnDelay = new TickDelay();

    private TickDelay gracePeriod = new TickDelay();

    public void teleportPlayer(Player player) {
        playersFighting.add(player);
        player.getMovement().teleport(LMSMap.swRegion.bounds.randomPosition());
        start();
    }

    private void start() {
        World.startEvent(event -> {
            chestSpawnDelay.delaySeconds(45);
            startCountdown();

            while (playersFighting.size() == 1) { //TODO testing set it > 1
                if (!chestSpawnDelay.isDelayed()) {
                    chestSpawnDelay.delaySeconds(120);
                    LastManStandingCrate.spawnChest(LMSMap, playersFighting);
                }
                event.delay(1);
            }
        });
    }

    private void startCountdown() {
        playersFighting.forEach(player -> World.startEvent(e -> {
            player.lock();
            e.delay(1);
            player.forceText("3...");
            e.delay(2);
            player.forceText("2...");
            e.delay(2);
            player.forceText("1...");
            e.delay(2);
            player.forceText("GO!");
            player.unlock();

            gracePeriod.delay(10);
            player.sendMessage("The grace period has begun!");
            e.delay(10);
            player.sendMessage("The fight for survival has begun!");
        }));
    }


    private static DynamicMap createMap() {
        DynamicMap LMSMap = new DynamicMap();
        LMSMap.buildSw(13658, 2)
                .buildSe(13914, 2)
                .buildNw(13659, 2)
                .buildNe(13915, 2);
        return LMSMap;
    }

}
