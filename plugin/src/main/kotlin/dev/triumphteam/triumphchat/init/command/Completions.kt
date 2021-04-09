package dev.triumphteam.triumphchat.init.command

import dev.triumphteam.core.func.Initializer
import dev.triumphteam.triumphchat.TriumphChat

object Completions : Initializer<TriumphChat> {

    override fun initialize(plugin: TriumphChat) {
        with(plugin) {
            commands {
                completion("#empty") { emptyList() }
            }
        }
    }

}