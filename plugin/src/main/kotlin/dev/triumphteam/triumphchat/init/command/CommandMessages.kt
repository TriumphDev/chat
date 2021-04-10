package dev.triumphteam.triumphchat.init.command

import dev.triumphteam.core.func.Initializer
import dev.triumphteam.triumphchat.TriumphChat
import dev.triumphteam.triumphchat.func.sendTo
import dev.triumphteam.triumphchat.locale.Message

object CommandMessages : Initializer<TriumphChat> {

    override fun initialize(plugin: TriumphChat) {
        with(plugin) {
            commands {
                message("cmd.no.permission") { locale[Message.COMMAND_NO_PERMISSION].sendTo(it) }
                message("cmd.wrong.usage") { locale[Message.COMMAND_WRONG_USAGE].sendTo(it) }
            }
        }
    }

}