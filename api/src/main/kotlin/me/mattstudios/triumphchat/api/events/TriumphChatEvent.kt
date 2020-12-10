package me.mattstudios.triumphchat.api.events

import me.mattstudios.triumphchat.api.chat.Message
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class TriumphChatEvent(val message: Message) : Event(true), Cancellable {

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