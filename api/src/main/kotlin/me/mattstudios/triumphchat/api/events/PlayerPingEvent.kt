package me.mattstudios.triumphchat.api.events

import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class PlayerPingEvent() : Event(true) {

    private var isCancelled = false

    override fun getHandlers(): HandlerList {
        return HANDLERS_LIST
    }

    companion object {
        private val HANDLERS_LIST = HandlerList()
    }

}