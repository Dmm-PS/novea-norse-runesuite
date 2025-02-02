package io.ruin.network.incoming.handlers;

import io.ruin.api.buffer.InBuffer;
import io.ruin.api.filestore.utility.Huffman;
import io.ruin.api.protocol.Protocol;
import io.ruin.model.entity.player.Player;
import io.ruin.network.central.CentralClient;
import io.ruin.network.incoming.Incoming;
import io.ruin.services.Loggers;
import io.ruin.services.Punishment;
import io.ruin.utility.IdHolder;

@IdHolder(ids = {86, 59, 68, 48, 19, 3})
public class FriendsHandler implements Incoming {

    @Override
    public void handle(Player player, InBuffer in, int opcode) {
        String name;
        if(opcode == 3) {
            /**
             * Rank friend
             */
            int rank = in.readByteA();
            name = in.readString();
            CentralClient.sendClanRank(player.getUserId(), name, rank);
            return;
        }
        name = in.readString();
        if(opcode == 59) {
            /**
             * Add friend
             */
            CentralClient.sendSocialRequest(player.getUserId(), name, 1);
            return;
        }
        if(opcode == 68) {
            /**
             * Delete friend
             */
            CentralClient.sendSocialRequest(player.getUserId(), name, 2);
            return;
        }
        if(opcode == 86) {
            /**
             * Add ignore
             */
            CentralClient.sendSocialRequest(player.getUserId(), name, 3);
            return;
        }
        if(opcode == 48) {
            /**
             * Delete ignore
             */
            CentralClient.sendSocialRequest(player.getUserId(), name, 4);
            return;
        }
        if(opcode == 19) {
            /**
             * Private message
             */
            String message = Huffman.decrypt(in, 100);
            if(Punishment.isMuted(player)) {
                if(player.shadowMute)
                    player.getPacketSender().write(Protocol.outgoingPm(name, message));
                else
                    player.sendMessage("You're muted and can't talk.");
                return;
            }
            CentralClient.sendPrivateMessage(player.getUserId(), player.getClientGroupId(), name, message);
            Loggers.logPrivateChat(player.getUserId(), player.getName(), player.getIp(), name, message);
            return;
        }
    }

}