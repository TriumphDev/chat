@file:JvmName("AdventureUtils")

package me.mattstudios.triumphchat.func

import me.mattstudios.msg.adventure.AdventureSerializer
import me.mattstudios.msg.base.MessageOptions
import me.mattstudios.msg.base.internal.nodes.MessageNode
import me.mattstudios.triumphchat.api.ChatUser
import me.mattstudios.triumphchat.api.Message
import me.mattstudios.triumphchat.config.bean.objects.FormatDisplay
import me.mattstudios.triumphchat.config.bean.objects.elements.ClickData
import net.kyori.adventure.identity.Identity
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.serializer.plain.PlainComponentSerializer
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.entity.Player

/**
 * Utility for simple component builder
 */
internal inline fun buildComponent(builderAction: TextComponent.Builder.() -> Unit): Component {
    return Component.text().apply(builderAction).build()
}

/**
 * Extension to simplify parsing PAPI placeholders and more
 */
internal fun TextComponent.Builder.append(
    text: String,
    hover: List<String>,
    click: ClickData,
    author: ChatUser?,
    recipient: ChatUser?
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

/**
 * Extension to add node appender to [TextComponent.Builder]
 */
internal fun TextComponent.Builder.append(nodes: List<MessageNode>) = append(nodes.toComponent())

/**
 * Adds a sendMessage for [Player] that takes in a [Component] instead
 */
internal fun Player.sendMessage(identity: Identity, component: Component) {
    AUDIENCES.player(this).sendMessage(identity, component)
}

/**
 * Adds a sendMessage for [ConsoleCommandSender] that takes in a [Component] instead
 */
internal fun ConsoleCommandSender.sendMessage(component: Component) {
    sendMessage(PlainComponentSerializer.INSTANCE.serialize(component))
}

/**
 * Adds a sendMessage for [ConsoleCommandSender] that takes in a [Component] instead
 */
internal fun ConsoleCommandSender.sendMessage(message: Message) {
    sendMessage(message.message)
}

/**
 *  Allows a list of [MessageNode] to be converted to [Component]
 */
internal fun List<MessageNode>.toComponent() = AdventureSerializer.toComponent(this)

/**
 * Allows a list of [MessageNode] to be converted to [Component] but with PAPI parsing
 */
internal fun FormatDisplay.toComponent(author: ChatUser? = null, recipient: ChatUser? = null): Component {
    return text
            .parsePAPI(author, recipient)
            .parseMarkdown(
                MessageOptions
                        .builder()
                        .setDefaultFormatData(createFormatData(hover, click, author, recipient))
                        .build()
            ).toComponent()
}