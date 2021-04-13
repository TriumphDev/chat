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