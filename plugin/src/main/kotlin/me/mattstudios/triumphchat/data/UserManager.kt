package me.mattstudios.triumphchat.data

import me.mattstudios.triumphchat.TriumphChat
import me.mattstudios.triumphchat.api.ChatUser
import org.bukkit.entity.Player
import java.util.UUID

class UserManager(private val plugin: TriumphChat) {

    private val users = mutableMapOf<UUID, ChatUser>()

    init {
        // load all players
    }

    fun getUser(uuid: UUID): ChatUser {
        return users[uuid] ?: addUser(uuid)
    }

    fun getUser(player: Player) = getUser(player.uniqueId)

    private fun addUser(uuid: UUID): ChatUser {
        return PlayerUser(plugin, uuid).apply {
            users[uuid] = this
        }
    }

}