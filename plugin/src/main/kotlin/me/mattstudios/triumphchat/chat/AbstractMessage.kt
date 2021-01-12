package me.mattstudios.triumphchat.chat

import me.mattstudios.msg.base.MessageOptions
import me.mattstudios.triumphchat.api.ChatPlayer
import me.mattstudios.triumphchat.api.Message
import me.mattstudios.triumphchat.api.events.PlayerPingEvent
import me.mattstudios.triumphchat.component.ComponentBuilder
import me.mattstudios.triumphchat.config.bean.objects.FormatDisplay
import me.mattstudios.triumphchat.config.bean.objects.MessageDisplay
import me.mattstudios.triumphchat.config.bean.objects.elements.ClickData
import me.mattstudios.triumphchat.extensions.nodes.PingPlayerNode
import me.mattstudios.triumphchat.func.MESSAGE_PLACEHOLDER
import me.mattstudios.triumphchat.func.buildComponent
import me.mattstudios.triumphchat.func.parseMarkdown
import net.kyori.adventure.text.Component
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
        return buildComponent {
            for (format in components) {
                if (format is MessageDisplay) {
                    append(format)
                    continue
                }

                append(format, author, recipient)
            }
        }
    }

    /**
     * Appends a message, either for console or for player
     */
    private fun ComponentBuilder.append(display: MessageDisplay) {
        with(display) {
            with(text.split(MESSAGE_PLACEHOLDER)) {
                for (i in indices) {
                    append(this[i], hover, click, author, recipient)
                    if (i != 0) continue

                    // Creating all the options for the main message
                    val options = MessageOptions.Builder(author.getFormats())
                    options.setDefaultColor(formatData.color)
                    //options.extensions(PING_EXTENSION)

                    append(options.build(), hover, click)
                }
            }
        }
    }

    /**
     * Special append function to handle pinging of players
     */
    private fun ComponentBuilder.append(
        options: MessageOptions,
        formatHover: List<String>? = null,
        formatClick: ClickData? = null
    ) {
        val nodes = rawMessage.parseMarkdown(options)

        for (node in nodes) {
            if (node !is PingPlayerNode) continue
            handlePing(node)
        }

        append(nodes, formatHover, formatClick, author, recipient)
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
