package me.mattstudios.triumphchat.component

import me.mattstudios.mfmsg.base.Message
import me.mattstudios.mfmsg.base.internal.Format
import me.mattstudios.mfmsg.base.internal.action.ClickAction
import me.mattstudios.mfmsg.base.internal.action.HoverAction
import me.mattstudios.mfmsg.base.internal.color.FlatColor
import me.mattstudios.mfmsg.base.internal.component.MessagePart
import me.mattstudios.mfmsg.base.internal.parser.MessageParser
import me.mattstudios.triumphchat.config.bean.objects.Click
import me.mattstudios.triumphchat.config.bean.objects.Component
import me.mattstudios.triumphchat.func.parsePAPI
import org.bukkit.entity.Player
import java.util.*

/**
 * @author Matt
 */
class ChatComponentBuilder {

    private val finalParts = mutableListOf<MessagePart>()
    private val current = mutableListOf<MessagePart>()

    fun append(message: String, formats: Set<Format> = EnumSet.allOf(Format::class.java)) {
        save()
        current.addAll(MessageParser(message, formats, FlatColor("white")).build())
    }

    fun append(component: Component, player: Player) {
        append(component.text.parsePAPI(player))
        addHover(component.hover.joinToString("\\n") { it.parsePAPI(player) })
        addClick(component.click, player)
    }

    fun addHover(hover: String) {
        if (hover.isEmpty()) return
        current.forEach { it.actions.add(HoverAction(Message.create().parse(hover).messageLines)) }
    }

    fun addClick(type: Format, click: String) {
        current.forEach { it.actions.add(ClickAction(type, click)) }
    }

    fun addClick(click: Click, player: Player) {
        val value = click.value
        val formatType = click.getFormat()

        if (formatType == null || value == null) return

        addClick(formatType, value.parsePAPI(player))
    }

    private fun save() {
        if (current.isEmpty()) return
        finalParts.addAll(current.toList())
        current.clear()
    }

    fun build(): ChatComponent {
        save()
        return ChatComponent(finalParts)
    }

}