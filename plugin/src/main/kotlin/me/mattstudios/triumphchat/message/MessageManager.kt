package me.mattstudios.triumphchat.message

import me.mattstudios.triumphchat.api.Message
import java.util.UUID

class MessageManager {

    private val messages = mutableMapOf<UUID, Message>()

    fun addMessage(to: UUID, message: Message) {
        messages[to] = message
    }

    fun hasReply(uuid: UUID) = messages[uuid] != null

}