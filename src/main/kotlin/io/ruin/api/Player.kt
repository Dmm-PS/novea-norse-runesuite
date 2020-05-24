package io.ruin.api

import io.ruin.model.entity.player.Player
import io.ruin.model.inter.InterfaceType

fun Player.openMainInterface(interId: Int) = openInterface(InterfaceType.MAIN, interId)

fun Player.openPrimaryOverlay(interId: Int) = openInterface(InterfaceType.PRIMARY_OVERLAY, interId)

fun Player.message(msg: String) = sendMessage(msg)