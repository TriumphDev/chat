package me.mattstudios.triumphchat.api

import net.kyori.adventure.text.Component
import org.bukkit.entity.Player

interface Message {
    val author: ChatPlayer
    var message: Component
    val mentionsList: MutableList<Player>
}