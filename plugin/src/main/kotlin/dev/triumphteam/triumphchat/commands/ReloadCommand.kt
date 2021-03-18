package dev.triumphteam.triumphchat.commands

import dev.triumphteam.core.func.color
import dev.triumphteam.triumphchat.TriumphChat
import dev.triumphteam.triumphchat.permissions.Permissions
import me.mattstudios.mf.annotations.Command
import me.mattstudios.mf.annotations.SubCommand
import me.mattstudios.mf.base.CommandBase
import org.bukkit.entity.Player

@Command("tchat")
class ReloadCommand(private val plugin: TriumphChat) : CommandBase() {

    private val userManager = plugin.userManager
    private val config = plugin.config

    @SubCommand("reload")
    @me.mattstudios.mf.annotations.Permission(Permissions.COMMAND_RELOAD)
    fun sendReply(sender: Player) {
        plugin.config.reload()
        plugin.formatsConfig.reload()
        sender.sendMessage("&aReloaded all configs!".color())
    }

}