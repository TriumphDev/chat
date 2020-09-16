package me.mattstudios.triumphchat.handlers

import me.mattstudios.core.configuration.Config
import me.mattstudios.triumphchat.component.ChatComponent
import me.mattstudios.triumphchat.config.Settings
import me.mattstudios.triumphchat.config.bean.objects.Component
import me.mattstudios.triumphchat.constants.Constant
import me.mattstudios.triumphchat.constants.Permission
import org.bukkit.entity.Player

/**
 * @author Matt
 * rest in piece Coli 2020-2020
 */
object FormatHandler {

    fun getMessage(player: Player, config: Config): ChatComponent {
        val formats = config[Settings.FORMATS]

        val format = formats
                .filter { player.hasPermission("${Permission.FORMAT.permission}${it.key}") }
                .maxByOrNull { it.value.priority }?.value ?: formats["default"] ?: Constant.DEFAULT_FORMAT

        val components = format.components

        val chatComponent = ChatComponent(format)

        components.forEach { (_, component) ->
            if ("%message%" in component.text) {
                chatComponent.append(handleMessage(component, player))
            } else {
                chatComponent.append(component.formatComponent(player))
            }
        }

        return chatComponent
    }

    private fun handleMessage(component: Component, player: Player): String {


        return component.formatComponent(player)
    }

}