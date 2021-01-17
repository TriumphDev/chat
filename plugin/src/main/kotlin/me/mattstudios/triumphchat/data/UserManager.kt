package me.mattstudios.triumphchat.data

import me.mattstudios.triumphchat.TriumphChat
import me.mattstudios.triumphchat.api.ChatUser
import org.bukkit.entity.Player
import java.util.UUID

class UserManager(private val plugin: TriumphChat) {

    private val players = mutableSetOf<ChatUser>()

    init {
        // load all players
    }

    fun getPlayer(uuid: UUID): ChatUser {
        return players.find { it.uuid == uuid } ?: addPlayer(uuid)
    }

    fun getPlayer(player: Player) = getPlayer(player.uniqueId)

    fun addPlayer(player: Player) = addPlayer(player.uniqueId)

    fun addPlayer(uuid: UUID): ChatUser {
        val user = PlayerUser(plugin, uuid)
        players.add(user)
        return user
    }

}