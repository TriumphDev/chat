package dev.triumphteam.triumphchat.permissions

import dev.triumphteam.triumphchat.func.MAIN_PERMISSION
import me.mattstudios.msg.base.internal.Format

object ChatPermission {
    private const val FORMAT = "${MAIN_PERMISSION}.chat.format"
    private const val COLOR = "${MAIN_PERMISSION}.chat.color"

    private const val MARKDOWN = "${FORMAT}.markdown"
    private const val LEGACY = "${FORMAT}.legacy"

    val formats = mapOf(
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

}