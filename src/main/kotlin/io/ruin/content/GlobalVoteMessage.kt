package io.ruin.content

//import io.ruin.api.globalEvent
import io.ruin.cache.Color
import io.ruin.model.World

/**
 * @author Leviticus
 */
object GlobalVoteMessage {

    private const val broadCastMessage = "Running low on gp?  ::Vote for 1M GP 1hour Double XP and a Vote Mystery Box!"
    private val yellMessage = Color.BLUE.wrap("[<img=129> Chris T]") + " " + "You can now ::Vote for 1M GP 1hour Double XP and a Vote Mystery Box!"

/*    init {
        globalEvent {
            while (true) {
                it.delay(10)
                broadcast(broadCastMessage)
                yell(yellMessage)
            }
        }
    }*/

    private fun broadcast(eventMessage: String) {
        for (p in World.players) {
            p.packetSender.sendMessage(eventMessage, "", 14)
        }
    }

    private fun yell(eventMessage: String) {
        for (p in World.players) {
            p.sendMessage(eventMessage)
        }
    }
}