/*
 * MIT License
 *
 * Copyright (c) 2021 Mateus Moreira
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.triumphteam.triumphchat.func

import dev.triumphteam.triumphchat.TriumphChat
import dev.triumphteam.triumphchat.config.bean.mapper.SettingsMapper
import dev.triumphteam.triumphchat.config.bean.objects.BaseDisplay
import dev.triumphteam.triumphchat.config.bean.objects.MessageDisplay
import dev.triumphteam.triumphchat.config.bean.objects.elements.ClickData
import dev.triumphteam.triumphchat.extensions.PingExtension
import me.mattstudios.msg.adventure.AdventureMessage
import me.mattstudios.msg.base.MessageOptions
import me.mattstudios.msg.base.internal.parser.MarkdownParser
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
    "sender" to BaseDisplay("&7%recipient_player_name% ", Optional.of(listOf("Sender information!"))),
    "you" to BaseDisplay("&e-> &7you "),
    "message" to MessageDisplay("&8> &f$MESSAGE_PLACEHOLDER", Optional.of(listOf("Sent @ %server_time%")))
)

val DEFAULT_FORMAT_MAP = mutableMapOf(
    "default" to DEFAULT_FORMAT,
    "default-pm-sender" to DEFAULT_PM_SENDER,
    "default-pm-recipient" to DEFAULT_PM_RECIPIENT
)
