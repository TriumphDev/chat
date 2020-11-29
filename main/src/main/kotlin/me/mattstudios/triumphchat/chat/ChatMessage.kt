package me.mattstudios.triumphchat.chat

import me.mattstudios.core.configuration.Config
import me.mattstudios.mfmsg.adventure.AdventureMessage
import me.mattstudios.mfmsg.base.MessageOptions
import me.mattstudios.triumphchat.component.ChatComponentBuilder
import me.mattstudios.triumphchat.config.Settings
import me.mattstudios.triumphchat.config.bean.ChatFormat
import me.mattstudios.triumphchat.config.bean.objects.MessageComponent
import me.mattstudios.triumphchat.func.AUDIENCE
import me.mattstudios.triumphchat.func.DEFAULT_FORMAT
import me.mattstudios.triumphchat.func.MESSAGE_PLACEHOLDER
import me.mattstudios.triumphchat.func.buildComponent
import me.mattstudios.triumphchat.permissions.FormatPermission
import me.mattstudios.triumphchat.permissions.Permission
import net.kyori.adventure.text.Component
import org.bukkit.event.player.AsyncPlayerChatEvent

class ChatMessage(
    event: AsyncPlayerChatEvent,
    private val config: Config
) : TriumphMessage {

    private val player = event.player
    private val rawMessage = event.message
    private val recipients = event.recipients

    override val format = selectFormat()
    override val message = createMessage()

    fun sendMessage() {
        recipients.forEach {
            AUDIENCE.audience(it).sendMessage(message)
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
     * Creates the component to send to the players
     */
    private fun createMessage(): Component {
        return buildComponent {
            for ((_, component) in format.components) {
                if (component is MessageComponent) handleMessage(component, this)
                else append(component, player)
            }
        }
    }

    /**
     * Handles the specific case of message, to allow only the placeholder to require specific formats
     */
    private fun handleMessage(messageComponent: MessageComponent, chatBuilder: ChatComponentBuilder) {
        val parts = messageComponent.text.split(MESSAGE_PLACEHOLDER)
        for (i in parts.indices) {
            chatBuilder.append(parts[i], messageComponent.formatHover, messageComponent.formatClick, player)

            if (i != 0) continue

            chatBuilder.append(
                AdventureMessage.create(
                    MessageOptions.Builder(FormatPermission.formatsForPlayer(player))
                            .setDefaultColor(messageComponent.defaultColor)
                            .build()
                ).parseToNodes(rawMessage),
                messageComponent.formatHover,
                messageComponent.formatClick,
                player
            )
        }
    }

}
