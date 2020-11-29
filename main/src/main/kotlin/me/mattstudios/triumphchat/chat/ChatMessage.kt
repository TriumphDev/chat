package me.mattstudios.triumphchat.chat

import me.mattstudios.core.configuration.Config
import me.mattstudios.mfmsg.adventure.AdventureMessage
import me.mattstudios.mfmsg.base.MessageOptions
import me.mattstudios.mfmsg.base.internal.Format
import me.mattstudios.triumphchat.api.chat.TriumphMessage
import me.mattstudios.triumphchat.config.Settings
import me.mattstudios.triumphchat.config.bean.ChatFormat
import me.mattstudios.triumphchat.config.bean.objects.MessageComponent
import me.mattstudios.triumphchat.func.AUDIENCE
import me.mattstudios.triumphchat.func.DEFAULT_FORMAT
import me.mattstudios.triumphchat.func.MESSAGE_PLACEHOLDER
import me.mattstudios.triumphchat.func.buildComponent
import me.mattstudios.triumphchat.func.parsePAPI
import me.mattstudios.triumphchat.permissions.FormatPermission
import me.mattstudios.triumphchat.permissions.Permission
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.event.player.AsyncPlayerChatEvent
import java.util.EnumSet

class ChatMessage(
    event: AsyncPlayerChatEvent,
    private val config: Config
) : TriumphMessage {

    private val player = event.player
    private val rawMessage = event.message
    private val recipients = event.recipients

    override val message = createChatMessage()
    override val consoleMessage = createConsoleMessage()

    /**
     * Sends the messages to players and console
     */
    fun sendMessage() {
        recipients.forEach {
            AUDIENCE.audience(it).sendMessage(message)
        }

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
                                MessageOptions.Builder(FormatPermission.formatsForPlayer(player))
                                        .setDefaultColor(component.defaultColor)
                                        .build()
                            ).parseToNodes(rawMessage),
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

}
