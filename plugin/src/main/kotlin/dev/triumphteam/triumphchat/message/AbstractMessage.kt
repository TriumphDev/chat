package dev.triumphteam.triumphchat.message

import dev.triumphteam.triumphchat.api.ChatUser
import dev.triumphteam.triumphchat.api.Message
import dev.triumphteam.triumphchat.api.events.PlayerPingEvent
import dev.triumphteam.triumphchat.config.bean.objects.FormatDisplay
import dev.triumphteam.triumphchat.config.bean.objects.MessageDisplay
import dev.triumphteam.triumphchat.extensions.nodes.PingPlayerNode
import dev.triumphteam.triumphchat.func.MESSAGE_PLACEHOLDER
import dev.triumphteam.triumphchat.func.append
import dev.triumphteam.triumphchat.func.buildComponent
import dev.triumphteam.triumphchat.func.parseMarkdown
import dev.triumphteam.triumphchat.func.toComponent
import me.mattstudios.msg.base.MessageOptions
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import org.bukkit.Sound
import org.bukkit.SoundCategory

abstract class AbstractMessage(
    override val author: ChatUser,
    private val recipient: ChatUser?,
    private val rawMessage: String,
    private val components: Collection<FormatDisplay>
) : Message {

    //private val config = plugin.config

    override val mentionsList = mutableListOf<ChatUser>()
    override var component = createChatMessage()

    /**
     * Creates the chat component
     */
    private fun createChatMessage(): Component {
        return buildComponent {
            components.forEach {
                if (it is MessageDisplay) append(it)
                else append(it.toComponent(author, recipient))
            }
        }
    }

    private fun TextComponent.Builder.append(display: MessageDisplay) = with(display) {
        text.split(MESSAGE_PLACEHOLDER).forEachIndexed { index, text ->
            append(text, hover, click, author, recipient)
            if (index == 0) {
                appendMessage(this)
            }
        }
    }

    /**
     * Special append function to handle pinging of players
     */
    private fun TextComponent.Builder.appendMessage(display: MessageDisplay) {
        val options = MessageOptions.Builder(author.getChatFormats())
        options.setDefaultFormatData(display.createFormatData(author, recipient))
        //options.extensions(PING_EXTENSION)

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
