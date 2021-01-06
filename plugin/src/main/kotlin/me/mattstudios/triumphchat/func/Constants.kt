package me.mattstudios.triumphchat.func

import me.mattstudios.msg.adventure.AdventureMessage
import me.mattstudios.msg.base.MessageOptions
import me.mattstudios.msg.base.internal.parser.MarkdownParser
import me.mattstudios.triumphchat.TriumphChat
import me.mattstudios.triumphchat.config.bean.mapper.SettingsMapper
import me.mattstudios.triumphchat.config.bean.objects.BaseDisplay
import me.mattstudios.triumphchat.config.bean.objects.MessageDisplay
import me.mattstudios.triumphchat.config.bean.objects.elements.ClickData
import me.mattstudios.triumphchat.extensions.PingExtension
import net.kyori.adventure.platform.bukkit.BukkitAudiences
import org.bukkit.plugin.java.JavaPlugin
import java.util.Optional

/**
 * OTHER
 */

val PING_EXTENSION = PingExtension()
val PROPERTY_MAPPER = SettingsMapper()

// Main permission constant
const val MAIN_PERMISSION = "triumphchat"

// Global MSG to be used by the components
val GLOBAL_MESSAGE = AdventureMessage.create()
val GLOBAL_PARSER = MarkdownParser(MessageOptions.builder().build())

// Global Audience to be used to send messages
val AUDIENCES = BukkitAudiences.create(JavaPlugin.getPlugin(TriumphChat::class.java))

/**
 * CONFIG
 */

val DEFAULT_FORMAT = mutableMapOf(
    "prefix" to BaseDisplay("%vault_prefix% ", clickData = Optional.of(ClickData("RUN_COMMAND", "ranks"))),
    "name" to BaseDisplay(
        "&f%player_name% ",
        Optional.of(listOf("Click to send a message")),
        Optional.of(ClickData("SUGGEST_COMMAND", "/msg %player_name% "))
    ),
    "message" to MessageDisplay(
        "&8> &f$MESSAGE_PLACEHOLDER",
        Optional.of(listOf("Sent @ %server_time%"))
    )
)

val DEFAULT_PM_SENDER = mutableMapOf(
    "you" to BaseDisplay("&7you &e-> "),
    "recipient" to BaseDisplay("&7%recipient_player_name% ", Optional.of(listOf("Recipient information!"))),
    "message" to MessageDisplay("&7: &f$MESSAGE_PLACEHOLDER", Optional.of(listOf("Sent @ %server_time%")))
)

val DEFAULT_PM_RECIPIENT = mutableMapOf(
    "sender" to BaseDisplay("&7%sender_player_name% ", Optional.of(listOf("Sender information!"))),
    "you" to BaseDisplay("&e-> &7you "),
    "message" to MessageDisplay("&8> &f$MESSAGE_PLACEHOLDER", Optional.of(listOf("Sent @ %server_time%")))
)

val DEFAULT_FORMAT_MAP = mutableMapOf(
    "default" to DEFAULT_FORMAT,
    "default-pm-sender" to DEFAULT_PM_SENDER,
    "default-pm-recipient" to DEFAULT_PM_RECIPIENT
)
