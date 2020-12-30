@file:JvmName("Utils")

package me.mattstudios.triumphchat.func

import me.clip.placeholderapi.PlaceholderAPI
import me.mattstudios.msg.base.FormatData
import me.mattstudios.msg.base.MessageOptions
import me.mattstudios.msg.base.internal.nodes.MessageNode
import me.mattstudios.msg.base.internal.nodes.TextNode
import me.mattstudios.msg.base.internal.parser.MarkdownParser
import me.mattstudios.triumphchat.api.ChatPlayer
import me.mattstudios.triumphchat.component.ComponentBuilder
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit

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
internal fun TextNode.copyFormat() = FormatData().also {
    it.color = color
    it.isBold = isBold
    it.isItalic = isItalic
    it.isStrike = isStrike
    it.isUnderlined = isUnderlined
    it.isObfuscated = isObfuscated
}

/**
 * Simple fun to parse PAPI placeholders
 */
internal fun String.parsePAPI(player: ChatPlayer? = null): String {
    return if (player == null) this else PlaceholderAPI.setPlaceholders(Bukkit.getPlayer(player.uuid), this)
}

internal fun String.parseMarkdown(messageOptions: MessageOptions? = null): List<MessageNode> {
    val options = messageOptions ?: MessageOptions.builder().build()
    return MarkdownParser(options).parse(this)
}

internal inline fun buildComponent(builderAction: ComponentBuilder.() -> Unit): Component {
    return ComponentBuilder().apply(builderAction).build()
}