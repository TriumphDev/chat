package dev.triumphteam.triumphchat

import dev.triumphteam.core.TriumphPlugin
import dev.triumphteam.core.func.log
import dev.triumphteam.core.locale.Language
import dev.triumphteam.triumphchat.commands.MessageCommand
import dev.triumphteam.triumphchat.commands.ReloadCommand
import dev.triumphteam.triumphchat.commands.ReplyCommand
import dev.triumphteam.triumphchat.config.FormatsConfig
import dev.triumphteam.triumphchat.config.settings.Setting
import dev.triumphteam.triumphchat.func.IS_PAPER
import dev.triumphteam.triumphchat.func.PROPERTY_MAPPER
import dev.triumphteam.triumphchat.init.Arguments
import dev.triumphteam.triumphchat.init.Completions
import dev.triumphteam.triumphchat.init.Messages
import dev.triumphteam.triumphchat.listeners.ChatListener
import dev.triumphteam.triumphchat.listeners.PlayerListener
import dev.triumphteam.triumphchat.locale.Message
import dev.triumphteam.triumphchat.managers.UserManager
import me.mattstudios.annotations.BukkitPlugin
import org.bukkit.Bukkit

@BukkitPlugin
class TriumphChat : TriumphPlugin() {

    lateinit var userManager: UserManager
        private set

    lateinit var formatsConfig: FormatsConfig
        private set

    override fun enable() {
        config(Setting, PROPERTY_MAPPER)
        locale(Message, Language.ENGLISH)

        formatsConfig = FormatsConfig(this)
        userManager = UserManager(this)

        displayStartupMessage()
        if (!checkPapi()) return
        validateFormats()

        commands {
            initialize(Arguments)
            initialize(Completions)
            initialize(Messages)

            register(
                MessageCommand(plugin),
                ReplyCommand(plugin),
                ReloadCommand(plugin)
            )
        }

        listeners {
            register(
                ChatListener(this@TriumphChat),
                PlayerListener(userManager)
            )
        }

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