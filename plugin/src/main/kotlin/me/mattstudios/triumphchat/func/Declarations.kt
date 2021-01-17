@file:JvmName("Utils")

package me.mattstudios.triumphchat.func

import me.clip.placeholderapi.PlaceholderAPI
import me.mattstudios.msg.base.FormatData
import me.mattstudios.msg.base.MessageOptions
import me.mattstudios.msg.base.internal.action.MessageAction
import me.mattstudios.msg.base.internal.nodes.MessageNode
import me.mattstudios.msg.base.internal.nodes.TextNode
import me.mattstudios.msg.base.internal.parser.MarkdownParser
import me.mattstudios.triumphchat.api.ChatUser
import me.mattstudios.triumphchat.config.FormatsConfig
import me.mattstudios.triumphchat.config.bean.objects.FormatDisplay
import me.mattstudios.triumphchat.config.bean.objects.elements.ClickData
import me.mattstudios.triumphchat.permissions.Permission
import org.apache.commons.lang.StringUtils
import org.bukkit.Bukkit

/**
 * Taken from PaperLib, just to check if the server is paper or not
 */
internal val IS_PAPER = try {
    Class.forName("com.destroystokyo.paper.PaperConfig")
    true
} catch (ignored: ClassNotFoundException) {
    false
}

/**
 * Copies the format data from a text node
 */
internal fun TextNode.copyFormat() = FormatData().also {
    it.color = color
    it.isBold = isBold
    it.isItalic = isItalic
    it.isStrike = isStrike
    it.isUnderlined = isUnderlined
    it.isObfuscated = isObfuscated
}

/**
 * Selects the format to use for the message
 */
internal fun ChatUser.selectMessageFormat(
    keys: Set<String>,
    formatsConfig: FormatsConfig,
    default: Map<String, FormatDisplay>
): Collection<FormatDisplay> {
    val player = Bukkit.getPlayer(uuid) ?: return default.values

    val formats = formatsConfig.getFormats()
    for (keyFormat in keys) {
        if (!player.hasPermission("${Permission.FORMAT.permission}.$keyFormat")) continue
        return formats[keyFormat]?.values ?: continue
    }

    return default.values
}

/**
 * Simple fun to parse PAPI placeholders
 */
fun String.parsePAPI(user: ChatUser? = null): String {
    return if (user == null) this else PlaceholderAPI.setPlaceholders(Bukkit.getPlayer(user.uuid), this)
}

/**
 * Requires 2 players instead, for parsing sender and recipient placeholders
 */
fun String.parsePAPI(sender: ChatUser?, recipient: ChatUser?): String {
    if (recipient == null) return parsePAPI(sender)
    return remove(SENDER_PLACEHOLDER).parsePAPI(sender).remove(RECIPIENT_PLACEHOLDER).parsePAPI(recipient)
}

/**
 * Helper function to remove the current placeholder used by [parsePAPI]
 */
private fun String.remove(oldValue: String): String = StringUtils.replace(this, oldValue, "")

/**
 * Function to parse all the Markdown into a list of [MessageNode]s
 */
fun String.parseMarkdown(messageOptions: MessageOptions? = null): List<MessageNode> {
    return MarkdownParser(messageOptions ?: MessageOptions.builder().build()).parse(this)
}

/**
 * Creates a hover event from [String] [List]
 */
fun List<String>.toHover(author: ChatUser?, recipient: ChatUser?): MessageAction {
    return MessageAction.from(joinToString("\\n").parsePAPI(author, recipient).parseMarkdown())
}

/**
 * TODO This
 */
fun createFormatData(hover: List<String>, click: ClickData, author: ChatUser?, recipient: ChatUser?): FormatData {
    val actions = mutableListOf<MessageAction>()
    if (hover.isNotEmpty()) {
        actions.add(hover.toHover(author, recipient))
    }

    if (click.isNotEmpty) {
        actions.add(click.createClick(author, recipient))
    }

    return FormatData().apply {
        if (actions.isNotEmpty()) this.actions = actions
    }
}
