package me.mattstudios.triumphchat.config.bean.objects

import me.mattstudios.triumphchat.func.parsePAPI
import org.bukkit.entity.Player

/**
 * @author Matt
 */
data class Component(
        var text: String = "",
        var hover: List<String> = emptyList(),
        var click: ClickAction = ClickAction()
) {

    fun formatComponent(player: Player): String {
        val papiParsedText = text.parsePAPI(player)
        if (papiParsedText.trim().isEmpty()) return papiParsedText

        val joinedHover = hover.joinToString("\n")
        if (joinedHover.isEmpty() && (click.type == null || click.value == null)) return text

        val formattedHover = buildString {
            append("hover: ")
            append(joinedHover.parsePAPI(player))
        }

        val formattedClick = click.getFormatted(player)

        return buildString {
            if (papiParsedText.startsWith(" ")) append(" ")
            append("[")
            append(papiParsedText.trim())
            append("](")
            if (joinedHover.isNotEmpty()) append(formattedHover)
            if (joinedHover.isNotEmpty() && formattedClick != null) append("|")
            if (formattedClick != null) append(formattedClick)
            append(")")
            if (papiParsedText.endsWith(" ")) append(" ")
        }
    }

}