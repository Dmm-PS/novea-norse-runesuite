package io.ruin.api

import io.ruin.model.World
import io.ruin.model.entity.npc.NPC
import io.ruin.model.entity.npc.NPCAction
import io.ruin.model.entity.player.Player
import io.ruin.model.inter.InterfaceHandler
import io.ruin.model.inter.actions.DefaultAction
import io.ruin.model.inter.actions.SimpleAction
import io.ruin.model.item.Item
import io.ruin.model.item.actions.ItemAction
import io.ruin.model.item.actions.ItemItemAction
import io.ruin.model.map.`object`.GameObject
import io.ruin.model.map.`object`.actions.ObjectAction
import io.ruin.process.event.Event
import io.ruin.process.event.EventConsumer
import kilim.Pausable

typealias PlayerObjectEvent = (Player, GameObject) -> Unit
typealias PlayerNpcEvent = (Player, NPC) -> Unit
typealias PlayerItemEvent = (Player, Item) -> Unit
typealias Runnable = (Event) -> Unit

fun whenObjClick(id: Int, option: Int, action: PlayerObjectEvent) = ObjectAction.register(id, option, action)

fun whenNpcClick(id: Int, option: Int, action: PlayerNpcEvent) = NPCAction.register(id, option, action)

fun whenButtonClick(parentId: Int, childId: Int, action: SimpleAction) {
    InterfaceHandler.register(parentId) {
        it.actions[childId] = action
    }
}

fun whenButtonClick(parentId: Int, childId: Int, action: DefaultAction) {
    InterfaceHandler.register(parentId) {
        it.actions[childId] = action
    }
}

/*fun Player.event(fn: (Event) -> Unit) {
    addEvent(fn)
}

fun NPC.event(fn: (Event) -> Unit) = startEvent(fn)

fun globalEvent(fn: (Event) -> Unit) = World.startEvent(fn)*/

fun whenItemOnItem(id: Int, id2: Int, fn: ItemItemAction) = ItemItemAction.register(id, id2, fn)

fun whenInvOption(id: Int, option: Int, fn: PlayerItemEvent) = ItemAction.registerInventory(id, option, fn)