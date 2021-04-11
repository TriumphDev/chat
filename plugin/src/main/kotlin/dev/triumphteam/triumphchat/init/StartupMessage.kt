package dev.triumphteam.triumphchat.init

import dev.triumphteam.core.func.Initializer
import dev.triumphteam.core.func.log
import dev.triumphteam.triumphchat.TriumphChat
import dev.triumphteam.triumphchat.func.IS_PAPER
import dev.triumphteam.triumphchat.func.log

/**
 * Object for displaying the startup message
 */
object StartupMessage : Initializer<TriumphChat> {

    /**
     * Simply logs the startup message to the console
     * Logs differently based on whether it's paper, pre 1.16.5 paper (due to console color changes), and spigot
     * @param plugin Instance of the plugin's main class
     */
    override fun initialize(plugin: TriumphChat) = with(plugin) {
        if (!IS_PAPER) {
            // TODO actually add something for spigoters lmao
            "Go die".log()
            return
        }

        // TODO make exceptions for 1.16.4-
        log {
            """
                
            <g:#FC466B:#3F5EFB>█▀▀ █░█ ▄▀█ ▀█▀ &8Version: &c${description.version}
            <g:#FC466B:#3F5EFB>█▄▄ █▀█ █▀█ ░█░ &8By: &cMatt
            
            """
        }

    }

}