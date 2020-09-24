package me.mattstudios.triumphchat.chat

import me.mattstudios.core.configuration.Config
import me.mattstudios.mfmsg.base.internal.color.FlatColor
import me.mattstudios.mfmsg.base.internal.color.MessageColor
import me.mattstudios.mfmsg.base.internal.color.handlers.ColorMapping
import me.mattstudios.mfmsg.base.internal.util.ColorUtils
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

    private var defaultColor: MessageColor = FlatColor("white")
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

                // Looks for legacy colors and stopping points
                val colorChar = matcher.group("char")
                if (colorChar != null) {
                    if (true) {
                        defaultColor = FlatColor(ColorMapping.fromChar(colorChar[0]))
                    } else {
                        if ("r".equals(colorChar, ignoreCase = true)) defaultColor = defaultColor
                    }
                }

                // Looks for hex colors
                val hex = matcher.group("hex")
                if (hex != null) {
                    defaultColor = FlatColor(ColorUtils.ofHex(hex))
                }

                // Looks for gradient
                val gradient = matcher.group("gradient")
                //if (gradient != null && !ServerVersion.CURRENT_VERSION.isColorLegacy) {
                    //defaultColor = ColorUtils.colorFromGradient(gradient)
                //}

                // Looks for rainbow
                //if (matcher.group("r") != null && !ServerVersion.CURRENT_VERSION.isColorLegacy) {
                    //defaultColor = ColorUtils.colorFromRainbow(matcher.group("sat"), matcher.group("lig"))
                //}

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
                append(chatComponent, before, component.hover, component.click, true)
            }

            if (identifier.isNotEmpty()) {
                append(chatComponent, identifier, emptyList(), component.click)
            }

            start = matcher.end()
            rest = rawMessage.substring(start)
        }

        if (rest.isNotEmpty()) {
            append(chatComponent, rest, component.hover, component.click, true)
        }

    }

    private fun append(
        chatComponent: ChatComponentBuilder,
        text: String,
        hover: List<String>,
        click: Click,
        shouldColor: Boolean = false
    ) {
        if (shouldColor) chatComponent.append(text, defaultColor = defaultColor)
        else chatComponent.append(text)
        chatComponent.addHover(hover.joinToString("\\n") { it.parsePAPI(player) })
        chatComponent.addClick(click, player)
    }

}