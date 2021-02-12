package me.mattstudios.triumphchat

import me.mattstudios.annotations.BukkitPlugin
import me.mattstudios.core.TriumphPlugin
import me.mattstudios.core.func.log
import me.mattstudios.mf.base.components.TypeResult
import me.mattstudios.triumphchat.api.ChatUser
import me.mattstudios.triumphchat.commands.MessageCommand
import me.mattstudios.triumphchat.commands.ReloadCommand
import me.mattstudios.triumphchat.commands.ReplyCommand
import me.mattstudios.triumphchat.config.FormatsConfig
import me.mattstudios.triumphchat.config.settings.Setting
import me.mattstudios.triumphchat.func.IS_PAPER
import me.mattstudios.triumphchat.func.PROPERTY_MAPPER
import me.mattstudios.triumphchat.func.sendTo
import me.mattstudios.triumphchat.listeners.ChatListener
import me.mattstudios.triumphchat.listeners.PlayerListener
import me.mattstudios.triumphchat.locale.Messages
import me.mattstudios.triumphchat.managers.UserManager
import org.bukkit.Bukkit

@BukkitPlugin
class TriumphChat : TriumphPlugin() {

    lateinit var userManager: UserManager
        private set

    lateinit var formatsConfig: FormatsConfig
        private set

    override fun enable() {
        config.create(Setting::class.java, PROPERTY_MAPPER)
        locale.setHolder(Messages::class.java).create()
        formatsConfig = FormatsConfig(this)

        userManager = UserManager(this)

        displayStartupMessage()
        if (!checkPapi()) return
        validateFormats()

        // Temporary
        registerParamType(ChatUser::class.java) { arg ->
            val player = Bukkit.getPlayer(arg.toString()) ?: return@registerParamType TypeResult(null, arg)
            return@registerParamType TypeResult(userManager.getUser(player), arg)
        }
        registerCompletion("#empty") { emptyList() }
        registerMessage("cmd.no.permission") { locale[Messages.COMMAND_NO_PERMISSION].sendTo(it) }
        registerMessage("cmd.wrong.usage") { locale[Messages.COMMAND_WRONG_USAGE].sendTo(it) }

        registerCommands(MessageCommand(this), ReplyCommand(this), ReloadCommand(this))
        registerListeners(ChatListener(this), PlayerListener(userManager))

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

        val invalidChatFormats = config[Setting.CHAT_FORMATS].formats.filter { it !in formats.keys }
        if (invalidChatFormats.isNotEmpty()) {
            "&6The following &7chat &6formats: &c${invalidChatFormats.joinToString("&6, &c")} &6are not registered in the &cformats.yml &6and will be ignored!".log()
        }

        val invalidSenderFormats = config[Setting.PRIVATE_MESSAGES].senderFormats.filter { it !in formats.keys }
        if (invalidSenderFormats.isNotEmpty()) {
            "&6The following &7sender &6formats: &c${invalidSenderFormats.joinToString("&6, &c")} &6are not registered in the &cformats.yml &6and will be ignored!".log()
        }

        val invalidRecipientFormats = config[Setting.PRIVATE_MESSAGES].recipientFormats.filter { it !in formats.keys }
        if (invalidRecipientFormats.isNotEmpty()) {
            "&6The following &7recipient &6formats: &c${invalidRecipientFormats.joinToString("&6, &c")} &6are not registered in the &cformats.yml &6and will be ignored!".log()
        }
    }

}