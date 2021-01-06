package me.mattstudios.triumphchat.func

import me.mattstudios.triumphchat.component.ComponentBuilder
import net.kyori.adventure.identity.Identity
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.plain.PlainComponentSerializer
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.entity.Player

internal inline fun buildComponent(builderAction: ComponentBuilder.() -> Unit): Component {
    return ComponentBuilder().apply(builderAction).build()
}

internal fun Player.sendMessage(identity: Identity, component: Component) =
    AUDIENCES.player(this).sendMessage(identity, component)

internal fun ConsoleCommandSender.sendMessage(component: Component) =
    sendMessage(PlainComponentSerializer.INSTANCE.serialize(component))