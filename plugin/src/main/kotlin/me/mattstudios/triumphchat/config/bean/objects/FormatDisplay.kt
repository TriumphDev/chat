package me.mattstudios.triumphchat.config.bean.objects

import me.mattstudios.msg.base.MessageOptions
import me.mattstudios.triumphchat.api.ChatPlayer
import me.mattstudios.triumphchat.config.bean.objects.elements.ClickData
import me.mattstudios.triumphchat.func.createFormatData
import me.mattstudios.triumphchat.func.parseMarkdown
import me.mattstudios.triumphchat.func.parsePAPI
import me.mattstudios.triumphchat.func.toComponent
import net.kyori.adventure.text.Component

/**
 * Interface for having both [BaseDisplay] and [MessageDisplay] together in the components of the formats
 */
interface FormatDisplay {
    val text: String
    val hover: List<String>
    val click: ClickData

    fun toComponent(author: ChatPlayer? = null, recipient: ChatPlayer? = null): Component {
        return text
                .parsePAPI(author, recipient)
                .parseMarkdown(
                    MessageOptions
                            .builder()
                            .setDefaultFormatData(createFormatData(hover, click, author, recipient))
                            .build()
                ).toComponent()
    }

}