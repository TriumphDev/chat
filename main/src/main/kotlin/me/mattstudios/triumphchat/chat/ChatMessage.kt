package me.mattstudios.triumphchat.chat

import me.mattstudios.core.configuration.Config
import me.mattstudios.msg.adventure.AdventureMessage
import me.mattstudios.msg.base.MessageOptions
import me.mattstudios.msg.base.internal.Format
import me.mattstudios.triumphchat.api.chat.TriumphMessage
import me.mattstudios.triumphchat.api.events.PlayerPingEvent
import me.mattstudios.triumphchat.component.ChatComponentBuilder
import me.mattstudios.triumphchat.config.Settings
import me.mattstudios.triumphchat.config.bean.ChatFormat
import me.mattstudios.triumphchat.config.bean.objects.Click
import me.mattstudios.triumphchat.config.bean.objects.MessageComponent
import me.mattstudios.triumphchat.extensions.nodes.PingMessageNode
import me.mattstudios.triumphchat.func.AUDIENCE
import me.mattstudios.triumphchat.func.DEFAULT_FORMAT
import me.mattstudios.triumphchat.func.MESSAGE_PLACEHOLDER
import me.mattstudios.triumphchat.func.PING_EXTENSION
import me.mattstudios.triumphchat.func.buildComponent
import me.mattstudios.triumphchat.func.parsePAPI
import me.mattstudios.triumphchat.permissions.ChatPermission
import me.mattstudios.triumphchat.permissions.Permission
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.entity.Player
import org.bukkit.event.player.AsyncPlayerChatEvent
import java.util.EnumSet

class ChatMessage(
    event: AsyncPlayerChatEvent,
    private val config: Config
) : TriumphMessage {

    private val player = event.player
    private val rawMessage = event.message
    private val recipients = event.recipients

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
            for ((_, component) in selectFormat().components) {
                if (component is MessageComponent) {
                    val parts = component.text.split(MESSAGE_PLACEHOLDER)
                    for (i in parts.indices) {
                        append(parts[i], component.formatHover, component.formatClick, player)

                        if (i != 0) continue

                        append(
                            AdventureMessage.create(
                                MessageOptions.Builder(ChatPermission.formatsForPlayer(player))
                                        .setDefaultColor(component.defaultColor)
                                        .extensions(PING_EXTENSION)
                                        .build()
                            ),
                            component.formatHover,
                            component.formatClick,
                            player
                        )
                    }

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
            val parts = config[Settings.CONSOLE_FORMAT].split(MESSAGE_PLACEHOLDER)
            for (i in parts.indices) {
                append(parts[i].parsePAPI(player), EnumSet.of(Format.COLOR))

                if (i != 0) continue

                append(
                    AdventureMessage.create(
                        MessageOptions.Builder(Format.NONE)
                                .build()
                    ).parseToNodes(rawMessage),
                    player = player
                )
            }
        }
    }

    /**
     * Selects the format to use for the message
     */
    private fun selectFormat(): ChatFormat {
        val formats = config[Settings.FORMATS]
        return formats
                .filter { player.hasPermission("${Permission.FORMAT.permission}.${it.key}") }
                .maxByOrNull { it.value.priority }?.value ?: formats["default"] ?: DEFAULT_FORMAT
    }

    /**
     * Special append function to handle pinging of players
     */
    private fun ChatComponentBuilder.append(
        message: AdventureMessage,
        formatHover: List<String>,
        formatClick: Click,
        player: Player
    ) {
        val nodes = message.parseToNodes(rawMessage)

        for (node in nodes) {
            if (node !is PingMessageNode) continue
            handlePing(node)
        }

        append(nodes, formatHover, formatClick, player)
    }

    /**
     * Handles the pinging
     */
    private fun handlePing(node: PingMessageNode) {
        val pingEvent = PlayerPingEvent()

        val nodePlayer = node.player
        nodePlayer.playSound(nodePlayer.location, Sound.BLOCK_NOTE_BLOCK_BELL, SoundCategory.MASTER, 1f, 1f)
        mentionsList.add(nodePlayer)
    }

}
