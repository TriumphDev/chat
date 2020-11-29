package me.mattstudios.triumphchat.permissions

import me.mattstudios.mfmsg.base.internal.Format
import me.mattstudios.triumphchat.func.MAIN_PERMISSION
import org.bukkit.entity.Player


enum class FormatPermission(
    private val permission: String,
    private val format: Format
) {

    BOLD("${FormatPermission.FORMAT_PERMISSION}.bold", Format.BOLD),
    ITALIC("${FormatPermission.FORMAT_PERMISSION}.italic", Format.ITALIC),
    UNDERLINE("${FormatPermission.FORMAT_PERMISSION}.underline", Format.UNDERLINE),
    COLOR("${FormatPermission.FORMAT_PERMISSION}.color", Format.COLOR),
    HEX("${FormatPermission.FORMAT_PERMISSION}.hex", Format.HEX);

    companion object {
        private const val FORMAT_PERMISSION = "${MAIN_PERMISSION}.format"

        fun formatsForPlayer(player: Player): Set<Format> {
            return values().filter { player.hasPermission(it.permission) }.map { it.format }.toSet()
        }

    }

}