package dev.triumphteam.triumphchat.api

import me.mattstudios.msg.base.internal.Format
import java.util.UUID

interface ChatUser {
    val uuid: UUID
    var replyTarget: UUID?

    /**
     * Sends a [Message] to the user
     */
    fun sendMessage(message: Message)

    /**
     * Gets the available [Format] the player has, based on permissions
     */
    fun getChatFormats(): Set<Format>

    companion object {}

}