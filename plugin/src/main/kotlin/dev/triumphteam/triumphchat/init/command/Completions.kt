package dev.triumphteam.triumphchat.init.command

import dev.triumphteam.core.func.Initializer
import dev.triumphteam.triumphchat.TriumphChat

/**
 *  Initializer object for anything to fo with command completions
 */
object Completions : Initializer<TriumphChat> {

    /**
     * Sets up all the completions for the plugin
     * @param plugin Instance of the plugin's main class
     */
    override fun initialize(plugin: TriumphChat) = with(plugin) {
        commands {
            completion("#empty") { emptyList() }
        }
    }

}