package dev.triumphteam.triumphchat.locale

import dev.triumphteam.triumphchat.TriumphChat
import org.bukkit.plugin.java.JavaPlugin

/**
 * This class is temporary until I a better way to handle messages
 * Soon™
 */
enum class DefaultMessage(
    private val en: String
) {

    COMMAND_WRONG_USAGE("&cWrong usage!"),
    COMMAND_NO_PERMISSION("&cYou don't have permission to use this command!"),
    MESSAGE_RECIPIENT_NO_EXIST("&cThe player &7{recipient} &cis not online!"),
    REPLY_NO_REPLY_TARGET("&cYou don't have a recipient to reply to!");

    fun getMessage(): String {
        return when (plugin.locale.language) {
            else -> en
        }
    }

    companion object {
        private val plugin = JavaPlugin.getPlugin(dev.triumphteam.triumphchat.TriumphChat::class.java)
    }

}