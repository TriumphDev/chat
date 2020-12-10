package me.mattstudios.triumphchat.extensions.nodes

import me.mattstudios.msg.base.internal.components.TextNode
import org.bukkit.entity.Player


class PingPlayerNode(text: String, val player: Player) : TextNode(text)