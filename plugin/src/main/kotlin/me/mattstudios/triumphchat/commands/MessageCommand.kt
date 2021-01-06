package me.mattstudios.triumphchat.commands

import me.mattstudios.mf.annotations.Alias
import me.mattstudios.mf.annotations.Command
import me.mattstudios.mf.annotations.Completion
import me.mattstudios.mf.annotations.Default
import me.mattstudios.mf.base.CommandBase
import me.mattstudios.triumphchat.TriumphChat
import me.mattstudios.triumphchat.api.ChatPlayer
import me.mattstudios.triumphchat.config.settings.Settings
import me.mattstudios.triumphchat.func.RECIPIENT_PLACEHOLDER
import me.mattstudios.triumphchat.func.SENDER_PLACEHOLDER
import me.mattstudios.triumphchat.func.parsePAPI
import org.apache.commons.lang.StringUtils
import org.bukkit.entity.Player

@Command("msg")
@Alias("m")
class MessageCommand(plugin: TriumphChat) : CommandBase() {

    private val playerManager = plugin.playerManager
    private val messageManager = plugin.messageManager
    private val config = plugin.config

    @Default
    fun sendMessage(
        sender: Player,
        @Completion("#players") to: ChatPlayer?,
        @Completion("#empty") args: Array<String>
    ) {
        if (to == null) {
            sender.sendMessage("Temp error")
            return
        }

        val author = playerManager.getPlayer(sender)
        val temp = config[Settings.PRIVATE_MESSAGES]
        //temp.recipientFormat.text.parsePAPI(author, to)
        //sender.sendMessage(temp.senderFormat.text)
    }

    private fun String.parsePAPI(sender: ChatPlayer, recipient: ChatPlayer) {
        val replaced = remove(SENDER_PLACEHOLDER).parsePAPI(sender).remove(RECIPIENT_PLACEHOLDER).parsePAPI(recipient)
    }

    fun String.remove(oldValue: String): String = StringUtils.replace(this, oldValue, "")

}