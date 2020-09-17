package me.mattstudios.triumphchat.component

import me.mattstudios.mfmsg.base.bukkit.nms.NmsMessage
import me.mattstudios.mfmsg.base.internal.component.MessageLine
import me.mattstudios.mfmsg.base.internal.component.MessagePart
import me.mattstudios.mfmsg.base.serializer.JsonSerializer
import org.bukkit.entity.Player

/**
 * @author Matt
 */
class ChatComponent(private val parts: List<MessagePart>) {

    fun sendMessage(player: Player) {
        NmsMessage.sendMessage(player, JsonSerializer.toString(listOf(MessageLine(parts))))
    }

}