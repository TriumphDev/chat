package dev.triumphteam.triumphchat.init.command

import dev.triumphteam.core.func.Initializer
import dev.triumphteam.triumphchat.TriumphChat
import dev.triumphteam.triumphchat.func.sendTo
import dev.triumphteam.triumphchat.locale.Message

/**
 *  Initializer object for anything to fo with command messages
 */
object CommandMessages : Initializer<TriumphChat> {

    /**
     * Overrides the framework's messages and adds custom ones too
     * @param plugin Instance of the plugin's main class
     */
    override fun initialize(plugin: TriumphChat) = with(plugin) {
        commands {
            message("cmd.no.permission") { locale[Message.COMMAND_NO_PERMISSION].sendTo(it) }
            message("cmd.wrong.usage") { locale[Message.COMMAND_WRONG_USAGE].sendTo(it) }
        }
    }

}