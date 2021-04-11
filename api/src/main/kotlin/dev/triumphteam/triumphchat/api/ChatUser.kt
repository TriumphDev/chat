package dev.triumphteam.triumphchat.api

import me.mattstudios.msg.base.internal.Format
import net.kyori.adventure.audience.ForwardingAudience
import net.kyori.adventure.identity.Identified
import java.util.UUID

interface ChatUser : ForwardingAudience.Single, Identified {
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

}