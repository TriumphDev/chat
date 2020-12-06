package me.mattstudios.triumphchat.permissions

import me.mattstudios.msg.base.internal.Format
import me.mattstudios.triumphchat.func.MAIN_PERMISSION
import org.bukkit.entity.Player

object ChatPermission {
    private const val FORMAT = "${MAIN_PERMISSION}.chat.format"
    private const val COLOR = "${MAIN_PERMISSION}.chat.color"

    private const val MARKDOWN = "${FORMAT}.markdown"
    private const val LEGACY = "${FORMAT}.legacy"

    private val formats = mapOf(
        // Markdown permissions
        "${MARKDOWN}.bold" to Format.BOLD,
        "${MARKDOWN}.italic" to Format.ITALIC,
        "${MARKDOWN}.underline" to Format.UNDERLINE,
        "${MARKDOWN}.strikethrough" to Format.STRIKETHROUGH,
        "${MARKDOWN}.magic" to Format.OBFUSCATED,

        // Legacy formatting permissions
        "${LEGACY}.bold" to Format.LEGACY_BOLD,
        "${LEGACY}.italic" to Format.LEGACY_ITALIC,
        "${LEGACY}.underline" to Format.LEGACY_UNDERLINE,
        "${LEGACY}.strikethrough" to Format.LEGACY_STRIKETHROUGH,
        "${LEGACY}.magic" to Format.LEGACY_OBFUSCATED,

        // Color related permissions
        "${COLOR}.color" to Format.COLOR,
        "${COLOR}.hex" to Format.HEX,
        "${COLOR}.gradient" to Format.GRADIENT,
        "${COLOR}.rainbow" to Format.RAINBOW
    )

    /**
     * Creates a set with the formats a player has based on it's permissions
     */
    fun formatsForPlayer(player: Player): Set<Format> {
        return formats.filter { player.hasPermission(it.key) }.values.toSet()
    }

}