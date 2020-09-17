package me.mattstudios.triumphchat.chat

import me.mattstudios.core.configuration.Config
import me.mattstudios.triumphchat.component.ChatComponent
import me.mattstudios.triumphchat.component.ChatComponentBuilder
import me.mattstudios.triumphchat.config.Settings
import me.mattstudios.triumphchat.config.bean.objects.Click
import me.mattstudios.triumphchat.config.bean.objects.Component
import me.mattstudios.triumphchat.constants.Constant
import me.mattstudios.triumphchat.constants.Permission
import me.mattstudios.triumphchat.func.parsePAPI
import org.bukkit.entity.Player

/**
 * @author Matt
 */
class ChatMessage(
    private val player: Player,
    private val rawMessage: String,
    private val recipients: MutableSet<Player>,
    private val config: Config
) {

    private val component = getMessage()

    fun sendMessage() {
        recipients.forEach {
            component.sendMessage(it)
        }
    }

    private fun getMessage(): ChatComponent {
        val formats = config[Settings.FORMATS]

        val format = formats
                .filter { player.hasPermission("${Permission.FORMAT.permission}${it.key}") }
                .maxByOrNull { it.value.priority }?.value ?: formats["default"] ?: Constant.DEFAULT_FORMAT

        val components = format.components

        val chatComponent = ChatComponentBuilder()

        components.forEach { (_, component) ->
            if ("%message%" in component.text) {
                handleMessage(component, chatComponent)
            } else {
                chatComponent.append(component, player)
            }
        }

        return chatComponent.build()
    }

    private fun handleMessage(component: Component, chatComponent: ChatComponentBuilder) {
        val message = component.text
        val matcher = Constant.MESSAGE_PATTERN.matcher(message)

        var rest = message
        var start = 0
        while (matcher.find()) {
            val identifier = matcher.group("message") ?: continue

            val before = message.substring(start, matcher.start())
            if (before.isNotEmpty()) append(chatComponent, before, component.hover, component.click)

            if (identifier.isNotEmpty()) {
                handleMessageLater(component, chatComponent)
            }

            start = matcher.end()
            rest = message.substring(start)
        }

        if (rest.isNotEmpty()) append(chatComponent, rest, component.hover, component.click)

    }

    private fun handleMessageLater(component: Component, chatComponent: ChatComponentBuilder) {
        val matcher = Constant.KEYWORD_PATTERN.matcher(rawMessage)

        var rest = rawMessage
        var start = 0
        while (matcher.find()) {
            val identifier = matcher.group("identifier") ?: continue

            val before = rawMessage.substring(start, matcher.start())
            if (before.isNotEmpty()) {
                append(chatComponent, before, component.hover, component.click)
                println(before)
            }

            if (identifier.isNotEmpty()) {
                append(chatComponent, identifier, emptyList(), component.click)
                println(identifier)
            }

            start = matcher.end()
            rest = rawMessage.substring(start)
        }

        if (rest.isNotEmpty()) {
            append(chatComponent, rest, component.hover, component.click)
            println(rest)
        }

    }

    private fun append(chatComponent: ChatComponentBuilder, text: String, hover: List<String>, click: Click) {
        chatComponent.append(text)
        chatComponent.addHover(hover.joinToString("\\n") { it.parsePAPI(player) })
        chatComponent.addClick(click, player)
    }

}