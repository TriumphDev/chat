package dev.triumphteam.triumphchat.extensions.nodes

import me.mattstudios.msg.base.internal.nodes.TextNode
import org.bukkit.entity.Player

class PingPlayerNode(text: String, val player: Player) : TextNode(text)