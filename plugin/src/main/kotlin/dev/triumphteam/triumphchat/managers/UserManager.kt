package dev.triumphteam.triumphchat.managers

import dev.triumphteam.triumphchat.TriumphChat
import dev.triumphteam.triumphchat.api.ChatUser
import dev.triumphteam.triumphchat.data.PlayerUser
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
        return PlayerUser(plugin, uuid).also {
            users[uuid] = it
        }
    }

}