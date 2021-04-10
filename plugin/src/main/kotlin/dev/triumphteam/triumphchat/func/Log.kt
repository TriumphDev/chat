package dev.triumphteam.triumphchat.func

import org.bukkit.Bukkit

/**
 * Simple logging function
 */
internal inline fun log(content: () -> String) {
    content.invoke().trimIndent().lines().forEach {
        AUDIENCES.sender(Bukkit.getConsoleSender()).sendMessage(GLOBAL_MESSAGE.parse(it))
    }
}