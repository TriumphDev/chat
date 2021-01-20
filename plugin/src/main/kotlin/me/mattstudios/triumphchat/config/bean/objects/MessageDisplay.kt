package me.mattstudios.triumphchat.config.bean.objects

import me.mattstudios.config.annotations.Name
import me.mattstudios.msg.base.FormatData
import me.mattstudios.msg.base.internal.nodes.TextNode
import me.mattstudios.triumphchat.api.ChatUser
import me.mattstudios.triumphchat.config.bean.objects.elements.ClickData
import me.mattstudios.triumphchat.func.MESSAGE_PLACEHOLDER
import me.mattstudios.triumphchat.func.addActions
import me.mattstudios.triumphchat.func.copyFormat
import me.mattstudios.triumphchat.func.parseMarkdown
import java.util.Optional

/**
 * Message display is used specifically when it comes to config displays that contain [MESSAGE_PLACEHOLDER]
 * It contains a [FormatData] object containing the formats necessary for the message to have
 */
data class MessageDisplay(
    override var text: String = "",
    @Name("hover") var hoverData: Optional<List<String>> = Optional.empty<List<String>>(),
    @Name("click") var clickData: Optional<ClickData> = Optional.empty()
) : FormatDisplay {

    override val hover: List<String> = hoverData.orElseGet { emptyList() }
    override val click: ClickData = clickData.orElseGet { ClickData() }

    /**
     * Gathers the format settings from the text node that has the message placeholder
     */
    fun createFormatData(author: ChatUser?, recipient: ChatUser?): FormatData {
        val data = text.parseMarkdown()
                .filterIsInstance(TextNode::class.java)
                .find { MESSAGE_PLACEHOLDER in it.text }
                ?.copyFormat() ?: FormatData()

        return data.addActions(hover, click, author, recipient)
    }

}