package dev.triumphteam.triumphchat.init

import dev.triumphteam.core.func.Initializer
import dev.triumphteam.core.func.log
import dev.triumphteam.triumphchat.TriumphChat
import dev.triumphteam.triumphchat.func.IS_PAPER

object StartupMessage : Initializer<TriumphChat> {

    override fun initialize(plugin: TriumphChat) = with(plugin) {
        if (!IS_PAPER) {
            "Go die".log()
            return
        }

        """
                
            &c█▀▀ █░█ ▄▀█ ▀█▀ &8Version: &c${description.version}
            &c█▄▄ █▀█ █▀█ ░█░ &8By: &cMatt
            
        """.trimIndent().lines().forEach(String::log)
    }

}