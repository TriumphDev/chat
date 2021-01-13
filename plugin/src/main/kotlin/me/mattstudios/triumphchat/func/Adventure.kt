package me.mattstudios.triumphchat.func

import me.mattstudios.msg.adventure.AdventureSerializer
import me.mattstudios.msg.base.MessageOptions
import me.mattstudios.msg.base.internal.nodes.MessageNode
import me.mattstudios.triumphchat.api.ChatPlayer
import me.mattstudios.triumphchat.component.ComponentBuilder
import me.mattstudios.triumphchat.config.bean.objects.elements.ClickData
import net.kyori.adventure.identity.Identity
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.serializer.plain.PlainComponentSerializer
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.entity.Player

internal inline fun buildComponent(builderAction: ComponentBuilder.() -> Unit): Component {
    return ComponentBuilder().apply(builderAction).build()
}

internal inline fun buildTemp(builderAction: TextComponent.Builder.() -> Unit): Component {
    return Component.text().apply(builderAction).build()
}

fun TextComponent.Builder.append(
    text: String,
    hover: List<String>,
    click: ClickData,
    author: ChatPlayer?,
    recipient: ChatPlayer?
) {
    append(
        text.parsePAPI(author, recipient).parseMarkdown(
            MessageOptions
                    .builder()
                    .setDefaultFormatData(createFormatData(hover, click, author, recipient))
                    .build()
        )
    )
}

fun TextComponent.Builder.append(nodes: List<MessageNode>) = append(nodes.toComponent())

internal fun List<String>.toAdventureHover(): HoverEvent<Component> {
    return HoverEvent.showText(joinToString("\\n").parseMarkdown().toComponent())
}

internal fun Player.sendMessage(identity: Identity, component: Component) {
    AUDIENCES.player(this).sendMessage(identity, component)
}

internal fun ConsoleCommandSender.sendMessage(component: Component) {
    sendMessage(PlainComponentSerializer.INSTANCE.serialize(component))
}

internal fun List<MessageNode>.toComponent() = AdventureSerializer.toComponent(this)