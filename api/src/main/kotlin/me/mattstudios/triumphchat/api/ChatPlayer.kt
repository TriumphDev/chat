package me.mattstudios.triumphchat.api

import me.mattstudios.msg.base.internal.Format
import java.util.UUID

interface ChatPlayer {
    val uuid: UUID

    /**
     * Sends a [Message] to the player
     */
    fun sendMessage(message: Message)

    /**
     * Gets the available [Format] the player has, based on permissions
     */
    fun getFormats(): Set<Format>
}