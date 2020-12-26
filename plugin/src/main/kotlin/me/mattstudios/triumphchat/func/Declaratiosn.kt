@file:JvmName("Utils")

package me.mattstudios.triumphchat.func

import me.clip.placeholderapi.PlaceholderAPI
import me.mattstudios.msg.base.internal.nodes.TextNode
import me.mattstudios.triumphchat.component.ChatComponentBuilder
import me.mattstudios.triumphchat.config.bean.objects.elements.FormatData
import net.kyori.adventure.text.Component
import org.bukkit.entity.Player

/**
 * Taken from PaperLib, just to check if the server is paper or not
 */
internal val IS_PAPER = try {
    Class.forName("com.destroystokyo.paper.PaperConfig")
    true
} catch (ignored: ClassNotFoundException) {
    false
}

// Config related

/**
 * Copies the format data from a text node
 */
internal fun TextNode.copyFormat() = FormatData(color, isBold, isItalic, isStrike, isUnderlined, isObfuscated)

/**
 * Simple fun to parse PAPI placeholders
 */
internal fun String.parsePAPI(player: Player?) =
    if (player == null) this else PlaceholderAPI.setPlaceholders(player, this)

internal inline fun buildComponent(builderAction: ChatComponentBuilder.() -> Unit): Component {
    return ChatComponentBuilder().apply(builderAction).build()
}