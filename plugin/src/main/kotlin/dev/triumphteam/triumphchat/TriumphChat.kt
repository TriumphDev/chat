package dev.triumphteam.triumphchat

import dev.triumphteam.core.TriumphPlugin
import dev.triumphteam.core.locale.Language
import dev.triumphteam.triumphchat.channel.ChannelManager
import dev.triumphteam.triumphchat.commands.MessageCommand
import dev.triumphteam.triumphchat.commands.ReloadCommand
import dev.triumphteam.triumphchat.commands.ReplyCommand
import dev.triumphteam.triumphchat.config.FormatsConfig
import dev.triumphteam.triumphchat.config.MainConfig
import dev.triumphteam.triumphchat.init.StartupMessage
import dev.triumphteam.triumphchat.init.checks.Dependencies
import dev.triumphteam.triumphchat.init.checks.ValidFormats
import dev.triumphteam.triumphchat.init.command.Arguments
import dev.triumphteam.triumphchat.init.command.CommandMessages
import dev.triumphteam.triumphchat.init.command.Completions
import dev.triumphteam.triumphchat.listeners.ChatListener
import dev.triumphteam.triumphchat.listeners.PlayerListener
import dev.triumphteam.triumphchat.locale.Message
import dev.triumphteam.triumphchat.managers.UserManager
import me.mattstudios.annotations.BukkitPlugin

/**
 * Main implementation of the plugin
 */
@BukkitPlugin
class TriumphChat : TriumphPlugin() {

    val userManager = UserManager(this)
    val channelManager = ChannelManager(this)

    override fun enable() {
        config(MainConfig)
        config(FormatsConfig)
        locale(Message, Language.ENGLISH)

        initialize(StartupMessage)

        if (!check(Dependencies)) return
        check(ValidFormats)

        commands {
            initialize(Arguments)
            initialize(Completions)
            initialize(CommandMessages)

            register(
                MessageCommand(plugin),
                ReplyCommand(plugin),
                ReloadCommand(plugin),
            )
        }

        listeners {
            register(
                ChatListener(plugin),
                PlayerListener(userManager),
            )
        }
    }

}