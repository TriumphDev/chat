package dev.triumphteam.triumphchat.extensions

import dev.triumphteam.triumphchat.extensions.nodes.PingPlayerNode
import dev.triumphteam.triumphchat.extensions.parser.PingParser
import dev.triumphteam.triumphchat.extensions.parser.nodes.PingNode
import me.mattstudios.msg.base.internal.action.HoverMessageAction
import me.mattstudios.msg.base.internal.action.content.HoverContent
import me.mattstudios.msg.base.internal.color.FlatColor
import me.mattstudios.msg.base.internal.nodes.TextNode
import me.mattstudios.msg.base.internal.renderer.FormatRetention
import me.mattstudios.msg.base.internal.renderer.NodeRenderer
import me.mattstudios.msg.commonmark.node.CustomNode
import me.mattstudios.msg.commonmark.parser.Parser
import me.mattstudios.msg.commonmark.parser.ParserExtension
import org.bukkit.Bukkit

/**
 * Parser extension to detect pings
 */
class PingExtension : ParserExtension, NodeRenderer {

    // Used to make sure
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

        node.actions = listOf(HoverMessageAction(HoverContent.showText("LichtHund\\n\\nCustomizable data here!")))

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