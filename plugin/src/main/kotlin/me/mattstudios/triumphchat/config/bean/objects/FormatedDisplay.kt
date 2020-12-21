package me.mattstudios.triumphchat.config.bean.objects

import me.mattstudios.config.annotations.Ignore
import me.mattstudios.config.annotations.Name
import me.mattstudios.msg.base.internal.components.TextNode
import me.mattstudios.triumphchat.config.bean.objects.elements.ClickData
import me.mattstudios.triumphchat.config.bean.objects.elements.FormatData
import me.mattstudios.triumphchat.func.GLOBAL_MESSAGE
import me.mattstudios.triumphchat.func.copyFormat
import java.util.Optional

data class MessageDisplay(
    override var text: String = "",
    @Name("hover") var hoverData: Optional<List<String>> = Optional.empty<List<String>>(),
    @Name("click") var clickData: Optional<ClickData> = Optional.empty(),
    @Ignore var placeholder: String = ""
) : FormatDisplay {

    override val hover: List<String> = hoverData.orElseGet { emptyList() }
    override val click: ClickData = clickData.orElseGet { ClickData() }

    val formatData = determineFormatData()

    /**
     * Gathers the format settings from the text node that has the message placeholder
     */
    private fun determineFormatData(): FormatData {
        val filteredNodes = GLOBAL_MESSAGE.parseToNodes(text).filterIsInstance(TextNode::class.java)

        if (placeholder.isEmpty()) {
            return filteredNodes.firstOrNull()?.copyFormat() ?: FormatData()
        }

        return filteredNodes.find { placeholder in it.text }?.copyFormat() ?: FormatData()
    }

}