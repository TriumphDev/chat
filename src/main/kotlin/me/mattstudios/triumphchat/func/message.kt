package me.mattstudios.triumphchat.func

import me.clip.placeholderapi.PlaceholderAPI
import org.bukkit.entity.Player

/**
 * @author Matt
 */

fun String.parsePAPI(player: Player?) = if (player == null) this else PlaceholderAPI.setPlaceholders(player, this)