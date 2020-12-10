package me.mattstudios.triumphchat.extensions.nodes.parser

import me.mattstudios.msg.commonmark.node.CustomNode


data class PingNode(val playerName: String) : CustomNode()