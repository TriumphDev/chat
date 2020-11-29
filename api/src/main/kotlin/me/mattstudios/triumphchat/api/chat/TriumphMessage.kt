package me.mattstudios.triumphchat.api.chat

import net.kyori.adventure.text.Component

interface TriumphMessage {

    val message: Component
    val consoleMessage: Component

}