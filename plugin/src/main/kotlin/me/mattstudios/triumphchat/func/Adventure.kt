package me.mattstudios.triumphchat.func

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.plain.PlainComponentSerializer
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.entity.Player

internal fun Player.sendMessage(component: Component) = AUDIENCE.player(this).sendMessage(component)
internal fun ConsoleCommandSender.sendMessage(component: Component) = sendMessage(PlainComponentSerializer.INSTANCE.serialize(component))