package io.ruin.content.npcs

import io.ruin.api.message
import io.ruin.api.whenNpcClick
import io.ruin.model.entity.npc.NPC
import io.ruin.model.entity.player.Player

/**
 * @author Leviticus
 */
object WiseOldMan {

    private const val wiseOldMan = 8407

    init {
        whenNpcClick(wiseOldMan, 1) { player, npc ->
            openTradingPost(player, npc)
        }
    }

    private fun openTradingPost(player: Player, npc: NPC) {
        if (player.gameMode.isIronMan) {
            player.message("No trading post for you ironmen - kevin!")
            return
        }
        player.tradePost.openViewOffers()
    }
}