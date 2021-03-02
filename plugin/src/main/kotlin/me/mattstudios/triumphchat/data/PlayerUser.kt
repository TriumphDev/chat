package me.mattstudios.triumphchat.data

import me.mattstudios.msg.base.internal.Format
import me.mattstudios.triumphchat.TriumphChat
import me.mattstudios.triumphchat.api.ChatUser
import me.mattstudios.triumphchat.api.Message
import me.mattstudios.triumphchat.func.sendMessage
import me.mattstudios.triumphchat.func.toPlayer
import me.mattstudios.triumphchat.permissions.ChatPermission
import net.kyori.adventure.identity.Identity
import org.bukkit.Bukkit
import java.util.UUID

data class PlayerUser(
    private val plugin: TriumphChat,
    override val uuid: UUID,
    override var replyTarget: UUID? = null
) : ChatUser {

    override fun sendMessage(message: Message) {
        uuid.toPlayer()?.sendMessage(Identity.identity(message.author.uuid), message.component)
    }

    override fun getChatFormats(): Set<Format> {
        val player = Bukkit.getPlayer(uuid) ?: return Format.NONE
        return ChatPermission.formats.filter { player.hasPermission(it.key) }.values.toSet()
    }

}