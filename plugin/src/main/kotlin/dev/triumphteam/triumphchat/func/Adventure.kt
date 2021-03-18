@file:JvmName("AdventureUtils")

package dev.triumphteam.triumphchat.func

import dev.triumphteam.triumphchat.api.ChatUser
import dev.triumphteam.triumphchat.api.Message
import dev.triumphteam.triumphchat.config.bean.objects.FormatDisplay
import dev.triumphteam.triumphchat.config.bean.objects.elements.ClickData
import me.mattstudios.msg.adventure.AdventureSerializer
import me.mattstudios.msg.base.FormatData
import me.mattstudios.msg.base.MessageOptions
import me.mattstudios.msg.base.internal.nodes.MessageNode
import net.kyori.adventure.identity.Identity
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.serializer.plain.PlainComponentSerializer
import org.bukkit.command.CommandSender
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
    append(text.parsePAPI(author, recipient).parseMarkdown(createDefaultOptions(hover, click, author, recipient)))
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
internal fun CommandSender.sendMessage(identity: Identity, component: Component) {
    if (this is Player) {
        sendMessage(identity, component)
        return
    }

    sendMessage(PlainComponentSerializer.INSTANCE.serialize(component))
}

/**
 * Adds a sendMessage for [ConsoleCommandSender] that takes in a [Component] instead
 */
internal fun ConsoleCommandSender.sendMessage(message: Message) {
    sendMessage(Identity.identity(message.author.uuid), message.component)
}

/**
 *  Allows a list of [MessageNode] to be converted to [Component]
 */
internal fun List<MessageNode>.toComponent() = AdventureSerializer.toComponent(this)

/**
 * Allows a list of [MessageNode] to be converted to [Component] but with PAPI parsing
 */
internal fun FormatDisplay.toComponent(author: ChatUser? = null, recipient: ChatUser? = null): Component {
    // TODO This is a duck taped solution
    val stripped = text.parsePAPI(author, recipient).remove("§r")
    return stripped
            .parseMarkdown(createDefaultOptions(hover, click, author, recipient))
            .toComponent()
}

private fun createDefaultOptions(
    hover: List<String>,
    click: ClickData,
    author: ChatUser?,
    recipient: ChatUser?
): MessageOptions? {
    return MessageOptions
            .builder()
            .setDefaultFormatData(FormatData().addActions(hover, click, author, recipient))
            .build()
}