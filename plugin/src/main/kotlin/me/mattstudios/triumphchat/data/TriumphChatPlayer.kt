package me.mattstudios.triumphchat.data

import me.mattstudios.msg.base.internal.Format
import me.mattstudios.triumphchat.api.ChatPlayer
import me.mattstudios.triumphchat.permissions.ChatPermission
import org.bukkit.Bukkit
import java.util.UUID

data class TriumphChatPlayer(
    override val uuid: UUID,
    var muted: Boolean = false
) : ChatPlayer {

    override fun getFormats(): Set<Format> {
        val player = Bukkit.getPlayer(uuid) ?: return Format.NONE
        return ChatPermission.formatsForPlayer(player)
    }

}