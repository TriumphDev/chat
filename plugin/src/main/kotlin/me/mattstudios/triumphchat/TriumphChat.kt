package me.mattstudios.triumphchat

import me.mattstudios.annotations.BukkitPlugin
import me.mattstudios.core.TriumphPlugin
import me.mattstudios.core.func.log
import me.mattstudios.mf.base.components.TypeResult
import me.mattstudios.triumphchat.api.ChatPlayer
import me.mattstudios.triumphchat.commands.MessageCommand
import me.mattstudios.triumphchat.commands.ReplyCommand
import me.mattstudios.triumphchat.config.FormatsConfig
import me.mattstudios.triumphchat.config.settings.Settings
import me.mattstudios.triumphchat.data.PlayerManager
import me.mattstudios.triumphchat.func.IS_PAPER
import me.mattstudios.triumphchat.func.PROPERTY_MAPPER
import me.mattstudios.triumphchat.listeners.ChatListener
import me.mattstudios.triumphchat.message.MessageManager
import org.bukkit.Bukkit
import org.bukkit.event.Listener

@BukkitPlugin
class TriumphChat : TriumphPlugin(), Listener {

    val playerManager = PlayerManager()
    val messageManager = MessageManager()

    lateinit var formatsConfig: FormatsConfig
        private set

    override fun enable() {
        config.load(Settings::class.java, PROPERTY_MAPPER)
        formatsConfig = FormatsConfig(this)

        displayStartupMessage()
        if (!checkPapi()) return
        validateFormats()

        registerParamType(ChatPlayer::class.java) { arg ->
            val player = Bukkit.getPlayer(arg.toString()) ?: return@registerParamType TypeResult(null, arg)
            return@registerParamType TypeResult(playerManager.getPlayer(player), arg)
        }

        registerCommands(MessageCommand(this), ReplyCommand(this))
        registerListeners(ChatListener(this))
    }

    /**
     * Checks if PAPI is enabled and disables plugin if not
     */
    private fun checkPapi(): Boolean {
        if (!Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            "&cPlaceholderAPI is required to use this Plugin!".log()
            pluginLoader.disablePlugin(this)
            return false
        }

        "&aHooked into PlaceholderAPI successfully!".log()
        return true
    }

    /**
     * Displays the startup message of the plugin
     */
    private fun displayStartupMessage() {
        if (!IS_PAPER) {
            "Go die".log()
            return
        }

        """
                
            &c█▀▀ █░█ ▄▀█ ▀█▀ &8Version: &c${description.version}
            &c█▄▄ █▀█ █▀█ ░█░ &8By: &cMatt
            
        """.trimIndent().lines().forEach(String::log)
    }

    /**
     * Checks if there is any format without a message component
     */
    private fun validateFormats() {
        val formats = formatsConfig.getFormats()

        val invalidChatFormats = config[Settings.CHAT_FORMATS].formats.filter { it !in formats.keys }
        if (invalidChatFormats.isNotEmpty()) {
            "&6The following &7chat &6formats: &c${invalidChatFormats.joinToString("&6, &c")} &6are not registered in the &cformats.yml &6and will be ignored!".log()
        }

        val invalidSenderFormats = config[Settings.PRIVATE_MESSAGES].senderFormats.filter { it !in formats.keys }
        if (invalidSenderFormats.isNotEmpty()) {
            "&6The following &7sender &6formats: &c${invalidSenderFormats.joinToString("&6, &c")} &6are not registered in the &cformats.yml &6and will be ignored!".log()
        }

        val invalidRecipientFormats = config[Settings.PRIVATE_MESSAGES].recipientFormats.filter { it !in formats.keys }
        if (invalidRecipientFormats.isNotEmpty()) {
            "&6The following &7recipient &6formats: &c${invalidRecipientFormats.joinToString("&6, &c")} &6are not registered in the &cformats.yml &6and will be ignored!".log()
        }

        /*for ((name, format) in config[Settings.FORMATS]) {
            if (format.components.values.filterIsInstance<MessageDisplay>().count() == 0) {
                "&6No component with &7%message% &6placeholder was found for format &7\"$name\"&6.".log()
            }
        }*/
    }

}