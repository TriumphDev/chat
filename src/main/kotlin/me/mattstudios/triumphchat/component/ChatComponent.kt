package me.mattstudios.triumphchat.component


import me.mattstudios.mfmsg.base.internal.components.MessageNode
import me.mattstudios.mfmsg.bukkit.NmsMessage
import me.mattstudios.mfmsg.bukkit.serializer.NodeSerializer
import org.bukkit.entity.Player

/**
 * @author Matt
 */
class ChatComponent(private val parts: List<MessageNode>) {

    fun sendMessage(player: Player) {
        NmsMessage.sendMessage(player, NodeSerializer.toString(parts))
    }

}