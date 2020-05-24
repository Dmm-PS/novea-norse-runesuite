package io.ruin.content.items

import io.ruin.api.message
import io.ruin.api.whenInvOption
import io.ruin.api.whenItemOnItem
import io.ruin.model.entity.player.Player
import io.ruin.model.item.Item

/**
 * @author Heaven
 */
object Buckets {

    private val buckets = intArrayOf(6712, 1929, 9659)

    init {
        buckets.forEach {
            whenInvOption(it, 4) { player, item ->
                empty(player, item)
            }
        }
    }

    private fun empty(player: Player, item: Item) {
        val targetSlot = item.slot
        item.remove()
        player.inventory.set(targetSlot, Item(3727))
    }
}