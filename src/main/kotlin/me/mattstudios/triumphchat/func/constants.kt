package me.mattstudios.triumphchat.func

import me.mattstudios.mfmsg.adventure.AdventureMessage
import me.mattstudios.triumphchat.config.bean.ChatFormat
import me.mattstudios.triumphchat.config.bean.objects.BaseComponent
import me.mattstudios.triumphchat.config.bean.objects.Click
import me.mattstudios.triumphchat.config.bean.objects.MessageComponent
import java.util.Optional
import java.util.regex.Pattern

val MESSAGE_PLACEHOLDER = "%message%"

val DEFAULT_MESSAGE = AdventureMessage.create()

val DEFAULT_ENTRY = mutableMapOf<String, ChatFormat>()

val DEFAULT_FORMAT = ChatFormat(
    1, mutableMapOf(
        "prefix" to BaseComponent("%vault_prefix% ", click = Optional.of(Click("RUN_COMMAND", "ranks"))),
        "name" to BaseComponent(
            "&f%player_name%",
            Optional.of(listOf("Click to send a message")),
            Optional.of(Click("SUGGEST_COMMAND", "/msg %player_name% "))
        ),
        "message" to MessageComponent("&8> %message%", Optional.of(listOf("Sent @ %server_time%")))
    )
)

val MESSAGE_PATTERN: Pattern = Pattern.compile("(?<message>%message%)")
