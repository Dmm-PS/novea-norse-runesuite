package io.ruin.model.activities.lastmanstanding;

import io.ruin.model.entity.player.Player;
import io.ruin.model.entity.shared.LockType;
import io.ruin.model.inter.Interface;
import io.ruin.model.inter.InterfaceType;
import io.ruin.model.map.object.GameObject;
import io.ruin.model.map.object.actions.ObjectAction;

public class LastManStandingObject {

    private static final int OUTPOST_LADDER = 29092;
    private static final int SHRINE = 29091;

    static {
        /**
         * Outpost ladders
         */
        ObjectAction.register(OUTPOST_LADDER, 3411, 5767, 0, "climb", (player, obj) -> climbOutpostLadder(player, obj, player.getAbsY() == 5768));
        ObjectAction.register(OUTPOST_LADDER, 3491, 5843, 0, "climb", (player, obj) -> climbOutpostLadder(player, obj, player.getAbsY() == 5842));
        ObjectAction.register(OUTPOST_LADDER, 3481, 5784, 0, "climb", (player, obj) -> climbOutpostLadder(player, obj, player.getAbsX() == 3482));
        ObjectAction.register(OUTPOST_LADDER, 3503, 5826, 0, "climb", (player, obj) -> climbOutpostLadder(player, obj, player.getAbsX() == 3504));

        /**
         * Shrine
         */
        ObjectAction.register(SHRINE, "offer", LastManStandingObject::openShrine);
    }

    private static void openShrine(Player player, GameObject obj) {
        player.openInterface(InterfaceType.MAIN, Interface.LAST_MAN_STANDING_SHRINE);
        player.getPacketSender().sendAccessMask(Interface.LAST_MAN_STANDING_SHRINE, 2, 0, 27, 1054);
    }

    private static void climbOutpostLadder(Player player, GameObject obj, boolean climbDown) {
        player.startEvent(e -> {
            int absX = obj.x;
            int absY = obj.y;
            player.lock(LockType.FULL_DELAY_DAMAGE);
            player.animate(climbDown ? 827 : 828);
            e.delay(1);
            if(climbDown) {
                if(obj.direction == 0)
                    absY = obj.y + 1;
                if(obj.direction == 2)
                    absY = obj.y - 1;
                if(obj.direction == 3)
                    absX = obj.x - 1;
            } else {
                if(obj.direction == 0)
                    absY = obj.y - 1;
                if(obj.direction == 2)
                    absY = obj.y + 1;
                if(obj.direction == 3)
                    absX = obj.x + 1;
            }
            player.getMovement().teleport(absX, absY);
            player.unlock();
        });
    }
}
