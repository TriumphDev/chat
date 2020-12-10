package me.mattstudios.triumphchat.extensions.parser.nodes

import me.mattstudios.msg.commonmark.node.CustomNode


data class PingNode(val playerName: String) : CustomNode()