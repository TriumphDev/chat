package me.mattstudios.triumphchat.component

import me.mattstudios.mfmsg.base.MessageOptions
import me.mattstudios.mfmsg.base.internal.Format
import me.mattstudios.mfmsg.base.internal.action.ClickMessageAction
import me.mattstudios.mfmsg.base.internal.action.HoverMessageAction
import me.mattstudios.mfmsg.base.internal.action.MessageAction
import me.mattstudios.mfmsg.base.internal.action.content.HoverContent
import me.mattstudios.mfmsg.base.internal.color.FlatColor
import me.mattstudios.mfmsg.base.internal.color.MessageColor
import me.mattstudios.mfmsg.base.internal.components.MessageNode
import me.mattstudios.mfmsg.base.internal.components.TextNode
import me.mattstudios.mfmsg.base.internal.parser.MarkdownParser
import me.mattstudios.triumphchat.config.bean.objects.Click
import me.mattstudios.triumphchat.config.bean.objects.Component
import me.mattstudios.triumphchat.events.DEFAULT_MESSAGE
import me.mattstudios.triumphchat.func.parsePAPI
import org.bukkit.entity.Player

/**
 * @author Matt
 */
class ChatComponentBuilder {

    private val finalNodes = mutableListOf<MessageNode>()
    private val currentNodes = mutableListOf<MessageNode>()

    fun append(
        message: String,
        formats: Set<Format> = Format.ALL,
        defaultColor: MessageColor = FlatColor("white")
    ) {
        save()
        val parser = MarkdownParser(MessageOptions.builder(formats).setDefaultColor(defaultColor).build())
        currentNodes.addAll(parser.parse(message))
    }

    fun append(nodes: List<MessageNode>, hover: List<String>, click: Click, player: Player) {
        currentNodes.addAll(nodes)
        addHover(hover.joinToString("\\n") { it.parsePAPI(player) })
        addClick(click, player)
    }

    fun append(component: Component, player: Player) {
        append(component.text.parsePAPI(player))
        addHover(component.hover.joinToString("\\n") { it.parsePAPI(player) })
        addClick(component.click, player)
    }

    fun addHover(hover: String) {
        if (hover.isEmpty()) return
        currentNodes.filterIsInstance(TextNode::class.java).forEach {
            addAction(it, HoverMessageAction(HoverContent.showText(DEFAULT_MESSAGE.parseToNodes(hover))))
        }
    }

    private fun addClick(type: Format, click: String) {
        currentNodes.filterIsInstance(TextNode::class.java).forEach {
            val clickAction = ClickMessageAction(type, click)
            if (it.actions == null) {
                it.actions = mutableListOf<MessageAction>(clickAction)
            } else {
                it.actions?.add(clickAction)
            }
        }
    }

    fun addClick(click: Click, player: Player) {
        val value = click.value
        val formatType = click.getFormat()

        if (formatType == null || value == null) return

        addClick(formatType, value.parsePAPI(player))
    }

    private fun save() {
        if (currentNodes.isEmpty()) return
        finalNodes.addAll(currentNodes.toList())
        currentNodes.clear()
    }

    fun build(): ChatComponent {
        save()
        return ChatComponent(finalNodes)
    }

    private fun addAction(node: TextNode, action: MessageAction) {
        if (node.actions == null) {
            node.actions = mutableListOf(action)
        } else {
            node.actions?.add(action)
        }
    }

}