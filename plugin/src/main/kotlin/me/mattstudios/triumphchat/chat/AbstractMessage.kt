package me.mattstudios.triumphchat.chat

import me.mattstudios.msg.base.MessageOptions
import me.mattstudios.triumphchat.api.ChatPlayer
import me.mattstudios.triumphchat.api.Message
import me.mattstudios.triumphchat.api.events.PlayerPingEvent
import me.mattstudios.triumphchat.config.bean.objects.FormatDisplay
import me.mattstudios.triumphchat.config.bean.objects.MessageDisplay
import me.mattstudios.triumphchat.config.bean.objects.elements.ClickData
import me.mattstudios.triumphchat.extensions.nodes.PingPlayerNode
import me.mattstudios.triumphchat.func.MESSAGE_PLACEHOLDER
import me.mattstudios.triumphchat.func.PING_EXTENSION
import me.mattstudios.triumphchat.func.append
import me.mattstudios.triumphchat.func.buildTemp
import me.mattstudios.triumphchat.func.createFormatData
import me.mattstudios.triumphchat.func.parseMarkdown
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import org.bukkit.Sound
import org.bukkit.SoundCategory

abstract class AbstractMessage(
    override val author: ChatPlayer,
    private val recipient: ChatPlayer?,
    private val rawMessage: String,
    private val components: Collection<FormatDisplay>
) : Message {

    //private val config = plugin.config

    override val mentionsList = mutableListOf<ChatPlayer>()
    override var message = createChatMessage()

    /**
     * Creates the chat component
     */
    private fun createChatMessage(): Component {
        return buildTemp {
            for (format in components) {
                if (format is MessageDisplay) {
                    append(format)
                    continue
                }

                append(format.toComponent(author, recipient))
            }
        }
    }

    private fun TextComponent.Builder.append(display: MessageDisplay) = with(display) {
        text.split(MESSAGE_PLACEHOLDER).forEachIndexed { index, text ->
            append(text, hover, click, author, recipient)
            if (index == 0) {
                append(hover, click)
            }
        }
    }

    /**
     * Special append function to handle pinging of players
     */
    private fun TextComponent.Builder.append(hover: List<String>, click: ClickData) {
        val options = MessageOptions.Builder(author.getFormats())
        options.setDefaultFormatData(createFormatData(hover, click, author, recipient))
        options.extensions(PING_EXTENSION)
        val nodes = rawMessage.parseMarkdown(options.build())

        for (node in nodes) {
            if (node !is PingPlayerNode) continue
            handlePing(node)
        }

        append(nodes)
    }

    /**
     * Handles the pinging
     */
    private fun handlePing(node: PingPlayerNode) {
        val pingEvent = PlayerPingEvent()

        val nodePlayer = node.player
        nodePlayer.playSound(nodePlayer.location, Sound.BLOCK_NOTE_BLOCK_BELL, SoundCategory.MASTER, 1f, 1f)
        //mentionsList.add(nodePlayer)
    }

}
