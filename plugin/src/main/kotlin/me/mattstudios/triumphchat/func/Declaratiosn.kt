package me.mattstudios.triumphchat.func

import me.clip.placeholderapi.PlaceholderAPI
import me.mattstudios.triumphchat.component.ChatComponentBuilder
import net.kyori.adventure.text.Component
import org.bukkit.entity.Player

/**
 * Taken from PaperLib, just to check if the server is paper or not
 */
val IS_PAPER = try {
    Class.forName("com.destroystokyo.paper.PaperConfig")
    true
} catch (ignored: ClassNotFoundException) {
    false
}

/**
 * Simple fun to parse PAPI placeholders
 */
fun String.parsePAPI(player: Player?) = if (player == null) this else PlaceholderAPI.setPlaceholders(player, this)

inline fun buildComponent(builderAction: ChatComponentBuilder.() -> Unit): Component {
    return ChatComponentBuilder().apply(builderAction).build()
}