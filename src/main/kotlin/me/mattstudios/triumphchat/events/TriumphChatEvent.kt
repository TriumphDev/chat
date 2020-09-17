package me.mattstudios.triumphchat.events

import me.mattstudios.triumphchat.chat.ChatMessage
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList


/**
 * @author Matt
 */
class TriumphChatEvent(val chatMessage: ChatMessage) : Event(true), Cancellable {

    private var isCancelled = false

    override fun isCancelled(): Boolean {
        return isCancelled
    }

    override fun setCancelled(cancelled: Boolean) {
        this.isCancelled = cancelled
    }

    override fun getHandlers(): HandlerList {
        return HANDLERS_LIST
    }

    companion object {
        private val HANDLERS_LIST = HandlerList()
    }

}