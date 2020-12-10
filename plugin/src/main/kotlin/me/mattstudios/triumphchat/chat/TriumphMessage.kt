package me.mattstudios.triumphchat.chat

import me.mattstudios.msg.adventure.AdventureMessage
import me.mattstudios.msg.base.MessageOptions
import me.mattstudios.msg.base.internal.Format
import me.mattstudios.msg.commonmark.parser.ParserExtension
import me.mattstudios.triumphchat.TriumphChat
import me.mattstudios.triumphchat.api.chat.Message
import me.mattstudios.triumphchat.api.events.PlayerPingEvent
import me.mattstudios.triumphchat.component.ChatComponentBuilder
import me.mattstudios.triumphchat.config.Settings
import me.mattstudios.triumphchat.config.bean.ChatFormat
import me.mattstudios.triumphchat.config.bean.objects.Click
import me.mattstudios.triumphchat.config.bean.objects.MessageComponent
import me.mattstudios.triumphchat.extensions.nodes.PingPlayerNode
import me.mattstudios.triumphchat.func.AUDIENCE
import me.mattstudios.triumphchat.func.DEFAULT_FORMAT
import me.mattstudios.triumphchat.func.MESSAGE_PLACEHOLDER
import me.mattstudios.triumphchat.func.PING_EXTENSION
import me.mattstudios.triumphchat.func.buildComponent
import me.mattstudios.triumphchat.permissions.ChatPermission
import me.mattstudios.triumphchat.permissions.Permission
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.entity.Player
import java.util.EnumSet




class TriumphMessage(
    private val player: Player,
    private val rawMessage: String,
    private val recipients: Set<Player>,
    private val plugin: TriumphChat
) : Message {

    private val config = plugin.config

    override val mentionsList = mutableListOf<Player>()
    override val message = createChatMessage()
    override val consoleMessage = createConsoleMessage()

    /**
     * Sends the messages to players and console
     */
    fun sendMessage() {
        // Testing mentions list
        println(mentionsList)

        // Sending message to recipients
        recipients.forEach {
            AUDIENCE.audience(it).sendMessage(message)
        }

        // Sends console message
        AUDIENCE.audience(Bukkit.getConsoleSender()).sendMessage(consoleMessage)
    }

    /**
     * Creates the chat component to send to the players
     */
    private fun createChatMessage(): Component {
        return buildComponent {
            for (component in selectFormat().components.values) {
                if (component is MessageComponent) {
                    appendMessage(
                        component,
                        Format.ALL,
                        ChatPermission.formatsForPlayer(player),
                        listOf(PING_EXTENSION)
                    )
                    continue
                }

                append(component, player)
            }
        }
    }

    /**
     * Creates the component to send to the console
     */
    private fun createConsoleMessage(): Component {
        return buildComponent {
            appendMessage(MessageComponent(config[Settings.CONSOLE_FORMAT]), EnumSet.of(Format.COLOR), Format.NONE)
        }
    }

    /**
     * Selects the format to use for the message
     */
    private fun selectFormat(): ChatFormat {
        val formats = config[Settings.FORMATS]
        return formats.filter { player.hasPermission("${Permission.FORMAT.permission}.${it.key}") }
                .maxByOrNull { it.value.priority }?.value ?: formats["default"] ?: DEFAULT_FORMAT
    }

    /**
     * Special append function to handle pinging of players
     */
    private fun ChatComponentBuilder.append(
        message: AdventureMessage,
        formatHover: List<String>? = null,
        formatClick: Click? = null,
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
    private fun ChatComponentBuilder.appendMessage(
        component: MessageComponent,
        mainFormats: Set<Format> = Format.ALL,
        messageFormats: Set<Format>,
        extensions: List<ParserExtension> = emptyList()
    ) {
        val parts = component.text.split(MESSAGE_PLACEHOLDER)
        for (i in parts.indices) {
            append(parts[i], component.formatHover, component.formatClick, mainFormats, player)

            if (i != 0) continue

            val options = MessageOptions.Builder(messageFormats)
            options.setDefaultColor(component.defaultColor)
            options.extensions(extensions)

            append(
                AdventureMessage.create(options.build()),
                component.formatHover,
                component.formatClick,
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
