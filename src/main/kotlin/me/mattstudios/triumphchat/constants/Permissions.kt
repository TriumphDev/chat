package me.mattstudios.triumphchat.constants

import me.mattstudios.mfmsg.base.internal.Format
import org.bukkit.entity.Player

/**
 * @author Matt
 */
enum class Permissions(
    private val permission: String,
    private val format: Format
) {

    BOLD("triumphcraft.bold", Format.BOLD);


    companion object {

        fun formatsFromPlayer(player: Player): Set<Format> {
            return values().filter { player.hasPermission(it.permission) }.map { it.format }.toSet()
        }

    }

}