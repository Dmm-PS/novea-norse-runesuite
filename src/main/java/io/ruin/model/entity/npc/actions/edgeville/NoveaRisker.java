package io.ruin.model.entity.npc.actions.edgeville;

import io.ruin.model.entity.shared.listeners.SpawnListener;

public class NoveaRisker {

    private static final int NOVEA_RISKER = 22;
    static {
        SpawnListener.register(NOVEA_RISKER, npc -> npc.startEvent(event -> {
            while (true) {
                npc.forceText("Ready to take a risk?");
                event.delay(500);
            }
        }));
    }

}
