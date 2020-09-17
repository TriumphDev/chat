package me.mattstudios.triumphchat.constants

import me.mattstudios.triumphchat.config.bean.ChatFormat
import me.mattstudios.triumphchat.config.bean.objects.Click
import me.mattstudios.triumphchat.config.bean.objects.Component
import java.util.regex.Pattern

object Constant {

    val DEFAULT_FORMAT = ChatFormat(
        1, mapOf(
            "prefix" to Component("%vault_prefix% ", click = Click("RUN_COMMAND", "ranks")),
            "name" to Component(
                "&f%player_name%",
                listOf("Click to send a message"),
                Click("SUGGEST_COMMAND", "/msg %player_name% ")
            ),
            "message" to Component("&8> &a%message%", listOf("Sent @ %server_time ....%"))
        )
    )

    val KEYWORD_PATTERN = Pattern.compile("(?<identifier>\\{.*?})")

    val MESSAGE_PATTERN = Pattern.compile("(?<message>%message%)")

}