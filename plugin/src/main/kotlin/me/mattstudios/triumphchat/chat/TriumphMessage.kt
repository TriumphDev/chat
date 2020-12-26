package me.mattstudios.triumphchat.chat

import me.mattstudios.msg.adventure.AdventureMessage
import me.mattstudios.msg.base.MessageOptions
import me.mattstudios.msg.base.internal.Format
import me.mattstudios.msg.commonmark.parser.ParserExtension
import me.mattstudios.triumphchat.TriumphChat
import me.mattstudios.triumphchat.api.chat.Message
import me.mattstudios.triumphchat.api.events.PlayerPingEvent
import me.mattstudios.triumphchat.component.ChatComponentBuilder
import me.mattstudios.triumphchat.config.bean.objects.FormatDisplay
import me.mattstudios.triumphchat.config.bean.objects.FormattedDisplay
import me.mattstudios.triumphchat.config.bean.objects.elements.ClickData
import me.mattstudios.triumphchat.extensions.nodes.PingPlayerNode
import me.mattstudios.triumphchat.func.AUDIENCE
import me.mattstudios.triumphchat.func.MESSAGE_PLACEHOLDER
import me.mattstudios.triumphchat.func.PING_EXTENSION
import me.mattstudios.triumphchat.func.buildComponent
import me.mattstudios.triumphchat.permissions.ChatPermission
import net.kyori.adventure.text.Component
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.entity.Player

class TriumphMessage(
    private val player: Player,
    private val rawMessage: String,
    private val recipients: Set<Player>,
    private val plugin: TriumphChat,
    private val components: Collection<FormatDisplay>
) : Message {

    private val configs = plugin.configs

    override val mentionsList = mutableListOf<Player>()
    override var message = createChatMessage()
    //val consoleMessage = createConsoleMessage()

    /**
     * Sends the messages to players and console
     */
    fun sendMessage() {
        // Testing mentions list
        //println(mentionsList)

        // Sending message to recipients
        recipients.forEach {
            AUDIENCE.player(it).sendMessage(message)
        }

        // Sends console message
        // AUDIENCE.sender(Bukkit.getConsoleSender()).sendMessage(consoleMessage)
    }

    /**
     * Creates the chat component to send to the players
     */
    private fun createChatMessage(): Component {
        return buildComponent {
            for (format in components) {
                if (format is FormattedDisplay) {
                    append(
                        format,
                        ChatPermission.formatsForPlayer(player),
                        listOf(PING_EXTENSION)
                    )
                    continue
                }

                append(format, player)
            }
        }
    }

    /**
     * Special append function to handle pinging of players
     */
    private fun ChatComponentBuilder.append(
        message: AdventureMessage,
        formatHover: List<String>? = null,
        formatClick: ClickData? = null,
        player: Player
    ) {
        val nodes = message.parseToNodes(rawMessage)

        for (node in nodes) {
            if (node !is PingPlayerNode) continue
            handlePing(node)
        }

        append(nodes, formatHover, formatClick, player)
    }

    /**
     * Appends a message, either for console or for player
     */
    private fun ChatComponentBuilder.append(
        display: FormattedDisplay,
        messageFormats: Set<Format>,
        extensions: List<ParserExtension> = emptyList(),
        mainFormats: Set<Format> = Format.ALL
    ) {
        val parts = display.text.split(MESSAGE_PLACEHOLDER)
        for (i in parts.indices) {
            append(parts[i], display.hover, display.click, mainFormats, player)

            if (i != 0) continue

            val options = MessageOptions.Builder(messageFormats)
            options.setDefaultColor(display.formatData.color)
            options.extensions(extensions)

            append(
                AdventureMessage.create(options.build()),
                display.hover,
                display.click,
                player
            )
        }
    }

    /**
     * Handles the pinging
     */
    private fun handlePing(node: PingPlayerNode) {
        val pingEvent = PlayerPingEvent()

        val nodePlayer = node.player
        nodePlayer.playSound(nodePlayer.location, Sound.BLOCK_NOTE_BLOCK_BELL, SoundCategory.MASTER, 1f, 1f)
        mentionsList.add(nodePlayer)
    }

}
