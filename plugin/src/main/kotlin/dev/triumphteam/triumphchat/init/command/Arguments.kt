package dev.triumphteam.triumphchat.init.command

import dev.triumphteam.core.func.Initializer
import dev.triumphteam.triumphchat.TriumphChat
import dev.triumphteam.triumphchat.api.ChatUser
import me.mattstudios.mf.base.components.TypeResult
import org.bukkit.Bukkit

/**
 *  Initializer object for anything to fo with command arguments
 */
object Arguments : Initializer<TriumphChat> {

    /**
     * Sets up the necessary plugin arguments for the command framework
     * @param plugin Instance of the plugin's main class
     */
    override fun initialize(plugin: TriumphChat) = with(plugin) {
        commands {
            parameter(ChatUser::class.java) { arg ->
                val player = Bukkit.getPlayer(arg.toString()) ?: return@parameter TypeResult(null, arg)
                return@parameter TypeResult(userManager.getUser(player), arg)
            }
        }
    }

}
