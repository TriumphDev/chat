package me.mattstudios.triumphchat.data

import me.mattstudios.triumphchat.api.ChatPlayer
import org.bukkit.entity.Player
import java.util.UUID

class PlayerManager {

    private val players = mutableSetOf<ChatPlayer>()

    init {
        // load all players
    }

    fun getPlayer(player: Player): ChatPlayer {
        return players.find { it.uuid == player.uniqueId } ?: TriumphChatPlayer(player.uniqueId)
    }

    fun addPlayer(player: Player) = addPlayer(player.uniqueId)

    fun addPlayer(uuid: UUID) {
        if (players.any { it.uuid == uuid }) return
        players.add(TriumphChatPlayer(uuid))
    }

    // Recipient storage

    private val recipientReplies: MutableMap<UUID, ChatPlayer> = mutableMapOf()

    fun addRecipientEntry(receiver: UUID, sender: ChatPlayer) {
        recipientReplies[receiver] = sender
    }

    fun getRecipientEntry(recipient: UUID): ChatPlayer? {
        return recipientReplies[recipient]
    }

}