package dev.triumphteam.triumphchat.init

import dev.triumphteam.core.func.Initializer
import dev.triumphteam.triumphchat.TriumphChat
import dev.triumphteam.triumphchat.api.ChatUser
import me.mattstudios.mf.base.components.TypeResult
import org.bukkit.Bukkit

object Arguments : Initializer<TriumphChat> {


    override fun initialize(plugin: TriumphChat) {
        with(plugin) {
            commands {
                parameter(ChatUser::class.java) { arg ->
                    val player = Bukkit.getPlayer(arg.toString()) ?: return@parameter TypeResult(null, arg)
                    return@parameter TypeResult(userManager.getUser(player), arg)
                }
            }
        }
    }
}