package me.mattstudios.triumphchat.chat

import me.mattstudios.triumphchat.config.bean.ChatFormat
import net.kyori.adventure.text.Component

interface TriumphMessage {

    val format: ChatFormat
    val message: Component

}