package me.mattstudios.triumphchat.func

import me.mattstudios.msg.adventure.AdventureMessage
import me.mattstudios.msg.base.MessageOptions
import me.mattstudios.msg.base.internal.parser.MarkdownParser
import me.mattstudios.triumphchat.TriumphChat
import me.mattstudios.triumphchat.config.bean.ChatFormat
import me.mattstudios.triumphchat.config.bean.objects.BaseDisplay
import me.mattstudios.triumphchat.config.bean.objects.PlaceholderDisplay
import me.mattstudios.triumphchat.config.bean.objects.elements.ClickData
import me.mattstudios.triumphchat.extensions.PingExtension
import net.kyori.adventure.platform.bukkit.BukkitAudiences
import org.bukkit.plugin.java.JavaPlugin
import java.util.Optional

/**
 * OTHER
 */

val PING_EXTENSION = PingExtension()

// Main permission constant
const val MAIN_PERMISSION = "triumphchat"

// Global MSG to be used by the components
val GLOBAL_MESSAGE = AdventureMessage.create()
val GLOBAL_PARSER = MarkdownParser(MessageOptions.builder().build())

// Global Audience to be used to send messages
val AUDIENCE = BukkitAudiences.create(JavaPlugin.getPlugin(TriumphChat::class.java))

/**
 * CONFIG
 */

val DEFAULT_FORMAT = ChatFormat(
    1, mutableMapOf(
        "prefix" to BaseDisplay("%vault_prefix% ", clickData = Optional.of(ClickData("RUN_COMMAND", "ranks"))),
        "name" to BaseDisplay(
            "&f%player_name% ",
            Optional.of(listOf("Click to send a message")),
            Optional.of(ClickData("SUGGEST_COMMAND", "/msg %player_name% "))
        ),
        "message" to PlaceholderDisplay(
            "&8> &f$MESSAGE_PLACEHOLDER",
            Optional.of(listOf("Sent @ %server_time%"))
        )
    )
)
