package dev.triumphteam.triumphchat.data

import dev.triumphteam.triumphchat.TriumphChat
import dev.triumphteam.triumphchat.api.ChatUser
import dev.triumphteam.triumphchat.api.Message
import dev.triumphteam.triumphchat.func.sendMessage
import dev.triumphteam.triumphchat.func.toPlayer
import dev.triumphteam.triumphchat.permissions.ChatPermission
import me.mattstudios.msg.base.internal.Format
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