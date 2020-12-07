package me.mattstudios.triumphchat.extensions

import me.mattstudios.msg.base.internal.action.HoverMessageAction
import me.mattstudios.msg.base.internal.action.content.HoverContent
import me.mattstudios.msg.base.internal.color.FlatColor
import me.mattstudios.msg.base.internal.components.TextNode
import me.mattstudios.msg.base.internal.renderer.FormatRetention
import me.mattstudios.msg.base.internal.renderer.NodeRenderer
import me.mattstudios.msg.commonmark.node.CustomNode
import me.mattstudios.msg.commonmark.parser.Parser
import me.mattstudios.msg.commonmark.parser.ParserExtension
import me.mattstudios.triumphchat.extensions.nodes.PingPlayerNode
import me.mattstudios.triumphchat.extensions.nodes.parser.PingNode
import me.mattstudios.triumphchat.extensions.parser.PingParser
import me.mattstudios.triumphchat.func.GLOBAL_MESSAGE
import org.bukkit.Bukkit


class PingExtension : ParserExtension, NodeRenderer {

    private var retention = FormatRetention.IGNORE

    override fun extend(builder: Parser.Builder) {
        builder.customTriggerProcessor(PingParser())
    }

    override fun getParserNode() = PingNode::class.java

    override fun render(customNode: CustomNode): TextNode {
        if (customNode !is PingNode) return emptyNode()
        val name = customNode.playerName

        val player = Bukkit.getPlayer(name) ?: return emptyNode("@$name")

        retention = FormatRetention.IGNORE

        val node = PingPlayerNode("@${player.name}", player)

        node.color = FlatColor("#378cdb")
        node.isUnderlined = true

        node.actions = listOf(HoverMessageAction(HoverContent.showText(GLOBAL_MESSAGE.parseToNodes("LichtHund\\n\\nCustomizable data here!"))))

        return node
    }

    override fun retention(): FormatRetention {
        return retention
    }

    private fun emptyNode(text: String = ""): TextNode {
        retention = FormatRetention.ALL
        return TextNode(text)
    }

}