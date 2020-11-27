package me.mattstudios.triumphchat.chat

import me.mattstudios.core.configuration.Config
import me.mattstudios.triumphchat.component.ChatComponentBuilder
import me.mattstudios.triumphchat.config.Settings
import me.mattstudios.triumphchat.config.bean.ChatFormat
import me.mattstudios.triumphchat.config.bean.objects.MessageComponent
import me.mattstudios.triumphchat.func.AUDIENCE
import me.mattstudios.triumphchat.func.DEFAULT_FORMAT
import me.mattstudios.triumphchat.permissions.Permission
import net.kyori.adventure.text.Component
import org.bukkit.event.player.AsyncPlayerChatEvent

class ConsoleMessage(
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
        val components = format.components

        val chatBuilder = ChatComponentBuilder()

        for ((_, component) in components) {
            if (component is MessageComponent) handleMessage(component, chatBuilder)
            else chatBuilder.append(component, player)
        }

        return chatBuilder.build()
    }

    /**
     * Handles the specific case of message, to allow only the placeholder to require specific formats
     */
    private fun handleMessage(messageComponent: MessageComponent, chatBuilder: ChatComponentBuilder) {

    }

}
