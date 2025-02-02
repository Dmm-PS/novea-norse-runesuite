package io.ruin.network.incoming.handlers;

import io.ruin.api.buffer.InBuffer;
import io.ruin.model.entity.player.Player;
import io.ruin.network.incoming.Incoming;
import io.ruin.utility.IdHolder;

@IdHolder(ids = {8})
public class TeleportHandler implements Incoming {

    @Override
    public void handle(Player player, InBuffer in, int opcode) {
        int z = in.readByteC();
        int unknown = in.readInt2();
        int y = in.readShortA();
        int x = in.readLEShortA();

        player.getMovement().teleport(x, y, z);
    }

}
