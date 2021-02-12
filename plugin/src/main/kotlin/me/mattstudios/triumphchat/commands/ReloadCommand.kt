package me.mattstudios.triumphchat.commands

import me.mattstudios.core.func.color
import me.mattstudios.mf.annotations.Command
import me.mattstudios.mf.annotations.Permission
import me.mattstudios.mf.annotations.SubCommand
import me.mattstudios.mf.base.CommandBase
import me.mattstudios.triumphchat.TriumphChat
import org.bukkit.entity.Player

@Command("tchat")
class ReloadCommand(private val plugin: TriumphChat) : CommandBase() {

    private val userManager = plugin.userManager
    private val config = plugin.config

    @SubCommand("reload")
    @Permission("triumphchat.reload")
    fun sendReply(sender: Player) {
        plugin.config.reload()
        plugin.formatsConfig.reload()
        sender.sendMessage("&aReloaded all configs!".color())
    }

}