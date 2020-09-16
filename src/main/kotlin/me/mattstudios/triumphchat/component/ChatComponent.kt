package me.mattstudios.triumphchat.component

import me.mattstudios.mfmsg.base.bukkit.nms.NmsMessage
import me.mattstudios.mfmsg.base.internal.Format
import me.mattstudios.mfmsg.base.internal.color.FlatColor
import me.mattstudios.mfmsg.base.internal.component.MessageLine
import me.mattstudios.mfmsg.base.internal.component.MessagePart
import me.mattstudios.mfmsg.base.internal.parser.MessageParser
import me.mattstudios.mfmsg.base.serializer.JsonSerializer
import me.mattstudios.triumphchat.config.bean.ChatFormat
import org.bukkit.entity.Player
import java.util.*

/**
 * @author Matt
 */
class ChatComponent(chatFormat: ChatFormat) {

    private val parts = mutableListOf<MessagePart>()

    fun append(message: String, formats: Set<Format> = EnumSet.allOf(Format::class.java)) {
        parts.addAll(MessageParser(message, formats, FlatColor("white")).build())
    }

    fun sendMessage(player: Player) {
        NmsMessage.sendMessage(player, toJson())
    }

    fun toJson() = JsonSerializer.toString(listOf(MessageLine(parts)))

}