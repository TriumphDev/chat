package me.mattstudios.triumphchat.config.bean.objects

import me.mattstudios.mfmsg.base.internal.color.FlatColor
import me.mattstudios.mfmsg.base.internal.color.MessageColor
import me.mattstudios.mfmsg.base.internal.components.TextNode
import me.mattstudios.triumphchat.func.GLOBAL_MESSAGE
import me.mattstudios.triumphchat.func.MESSAGE_PLACEHOLDER
import java.util.Optional

data class MessageComponent(
    override var text: String = "",
    var hover: Optional<List<String>> = Optional.empty<List<String>>(),
    var click: Optional<Click> = Optional.empty()
) : FormatComponent {

    override val formatHover = if (hover.isPresent) hover.get() else emptyList()
    override val formatClick = if (click.isPresent) click.get() else Click()

    val defaultColor = determineDefaultColor()

    /**
     * Parses the current text and determines the default color of the message
     */
    private fun determineDefaultColor(): MessageColor {
        return GLOBAL_MESSAGE.parseToNodes(text)
                .filterIsInstance(TextNode::class.java)
                .find { MESSAGE_PLACEHOLDER in it.text }?.color ?: return FlatColor("white")
    }

}