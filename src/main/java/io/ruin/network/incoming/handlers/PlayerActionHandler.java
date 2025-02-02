package io.ruin.network.incoming.handlers;

import io.ruin.api.buffer.InBuffer;
import io.ruin.model.World;
import io.ruin.model.entity.player.Player;
import io.ruin.model.entity.player.PlayerAction;
import io.ruin.network.incoming.Incoming;
import io.ruin.utility.IdHolder;

@IdHolder(ids={71, 46, 2, 85, 82, 28, 87, 89})
public class PlayerActionHandler implements Incoming {

    @Override
    public void handle(Player player, InBuffer in, int opcode) {
        if(player.isLocked())
            return;
        player.resetActions(true, true, true);

        int option = OPTIONS[opcode];
        if(option == 1) {
            int ctrlRun = in.readByte();
            int targetIndex = in.readShort();
            handle(player, option, targetIndex, ctrlRun);
            return;
        }
        if(option == 2) {
            int ctrlRun = in.readByteS();
            int targetIndex = in.readShort();
            handle(player, option, targetIndex, ctrlRun);
            return;
        }
        if(option == 3) {
            int ctrlRun = in.readByteA();
            int targetIndex = in.readShortA();
            handle(player, option, targetIndex, ctrlRun);
            return;
        }
        if(option == 4) {
            int targetIndex = in.readLEShortA();
            int ctrlRun = in.readByte();
            handle(player, option, targetIndex, ctrlRun);
            return;
        }
        if(option == 5) {
            int ctrlRun = in.readByteS();
            int targetIndex = in.readLEShortA();
            handle(player, option, targetIndex, ctrlRun);
            return;
        }
        if(option == 6) {
            int ctrlRun = in.readByteC();
            int targetIndex = in.readLEShortA();
            handle(player, option, targetIndex, ctrlRun);
            return;
        }
        if(option == 7) {
            int targetIndex = in.readLEShort();
            int ctrlRun = in.readByteA();
            handle(player, option, targetIndex, ctrlRun);
            return;
        }
        if(option == 8) {
            int targetIndex = in.readLEShortA();
            int ctrlRun = in.readByteC();
            handle(player, option, targetIndex, ctrlRun);
            return;
        }
        player.sendFilteredMessage("Unhandled player action: option=" + option + " opcode=" + opcode);
    }

    private static void handle(Player player, int option, int targetIndex, int ctrlRun) {
        Player target = World.getPlayer(targetIndex);
        if(target == null)
            return;
        if(targetIndex == player.getIndex())
            return;
        PlayerAction action = player.getAction(option);
        if(action == null)
            return;
        player.getMovement().setCtrlRun(ctrlRun == 1);
        action.consumer.accept(player, target);
    }

}