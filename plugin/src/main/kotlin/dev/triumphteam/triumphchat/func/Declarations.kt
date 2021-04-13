/*
 * MIT License
 *
 * Copyright (c) 2021 Mateus Moreira
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

@file:JvmName("Utils")

package dev.triumphteam.triumphchat.func

import dev.triumphteam.triumphchat.api.ChatUser
import dev.triumphteam.triumphchat.config.FormatsConfig
import dev.triumphteam.triumphchat.config.Config
import dev.triumphteam.triumphchat.config.bean.objects.FormatDisplay
import dev.triumphteam.triumphchat.config.bean.objects.MessageDisplay
import dev.triumphteam.triumphchat.config.bean.objects.elements.ClickData
import dev.triumphteam.triumphchat.config.settings.Setting
import dev.triumphteam.triumphchat.message.ChatMessage
import dev.triumphteam.triumphchat.permissions.Permissions
import me.clip.placeholderapi.PlaceholderAPI
import me.mattstudios.msg.base.FormatData
import me.mattstudios.msg.base.MessageOptions
import me.mattstudios.msg.base.internal.action.MessageAction
import me.mattstudios.msg.base.internal.nodes.MessageNode
import me.mattstudios.msg.base.internal.nodes.TextNode
import me.mattstudios.msg.base.internal.parser.MarkdownParser
import net.kyori.adventure.identity.Identity
import org.apache.commons.lang.StringUtils
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import java.util.UUID

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
        if (!player.hasPermission(Permissions.FORMAT.plus(keyFormat))) continue
        return formats[keyFormat]?.values ?: continue
    }

    return default.values
}

/**
 * Simple fun to parse PAPI placeholders
 */
internal fun String.parsePAPI(user: ChatUser? = null): String {
    return if (user == null) this else PlaceholderAPI.setPlaceholders(Bukkit.getPlayer(user.uuid), this)
}

/**
 * Requires 2 players instead, for parsing sender and recipient placeholders
 */
internal fun String.parsePAPI(sender: ChatUser?, recipient: ChatUser?): String {
    if (recipient == null) return parsePAPI(sender)
    return remove(SENDER_PLACEHOLDER).parsePAPI(sender).remove(RECIPIENT_PLACEHOLDER).parsePAPI(recipient)
}

/**
 * Helper function to remove the current placeholder used by [parsePAPI]
 */
fun String.remove(oldValue: String): String = StringUtils.replace(this, oldValue, "")

/**
 * Function to parse all the Markdown into a list of [MessageNode]s
 */
internal fun String.parseMarkdown(messageOptions: MessageOptions? = null): List<MessageNode> {
    return MarkdownParser(messageOptions ?: MessageOptions.builder().build()).parse(this)
}

/**
 * Creates a hover event from [String] [List]
 */
internal fun List<String>.toHover(author: ChatUser?, recipient: ChatUser?): MessageAction {
    return MessageAction.from(joinToString("\\n").parsePAPI(author, recipient).parseMarkdown())
}

/**
 * Util to get player from UUID
 */
internal fun UUID.toPlayer() = Bukkit.getPlayer(this)

internal fun String.sendTo(sender: CommandSender) = sender.sendMessage(Identity.nil(), GLOBAL_MESSAGE.parse(this))

/**
 * TODO This
 */
internal fun FormatData.addActions(
    hover: List<String>,
    click: ClickData,
    author: ChatUser?,
    recipient: ChatUser?
): FormatData {
    val actions = mutableListOf<MessageAction>()
    if (hover.isNotEmpty()) {
        actions.add(hover.toHover(author, recipient))
    }

    if (click.isNotEmpty) {
        actions.add(click.toClick(author, recipient))
    }

    return apply {
        if (actions.isNotEmpty()) this.actions = actions
    }
}

fun ChatUser.sendPrivateMessage(sender: ChatUser, message: String, plugin: dev.triumphteam.triumphchat.TriumphChat) {
    val senderMessage = ChatMessage(
        sender,
        this,
        message,
        this.selectMessageFormat(
            plugin.config<Config>()[Setting.PRIVATE_MESSAGES].senderFormats,
            plugin.config<FormatsConfig>(),
            DEFAULT_PM_SENDER
        )
    )

    val recipientMessage = ChatMessage(
        this,
        sender,
        message,
        sender.selectMessageFormat(
            plugin.config<Config>()[Setting.PRIVATE_MESSAGES].recipientFormats,
            plugin.config<FormatsConfig>(),
            DEFAULT_PM_RECIPIENT
        )
    )

    this.sendMessage(recipientMessage)
    sender.sendMessage(senderMessage)
    this.replyTarget = sender.uuid
    sender.replyTarget = uuid

    if (uuid.toPlayer()?.hasPermission(Permissions.SOCIAL_SPY__BYPASS) != false) {
        return
    }

    if (sender.uuid.toPlayer()?.hasPermission(Permissions.SOCIAL_SPY__BYPASS) != false) {
        return
    }

    val socialSpyMessage = ChatMessage(
        sender,
        this,
        message,
        listOf(MessageDisplay(plugin.config<Config>()[Setting.PRIVATE_MESSAGES].socialSpyFormat))
    )

    Bukkit.getOnlinePlayers()
        .filter { it.uniqueId != uuid && it.uniqueId != sender.uuid }
        .filter { it.hasPermission(Permissions.SOCIAL_SPY) }
        .forEach { plugin.userManager.getUser(it.uniqueId).sendMessage(socialSpyMessage) }
}
