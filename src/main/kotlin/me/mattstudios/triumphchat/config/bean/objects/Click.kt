package me.mattstudios.triumphchat.config.bean.objects

import me.mattstudios.mfmsg.base.internal.Format

/**
 * @author Matt
 */
data class Click(
        var type: String? = null,
        var value: String? = null
) {

    fun getFormat() = when (type?.toUpperCase()) {
        "SUGGEST_COMMAND" -> Format.ACTION_SUGGEST
        "RUN_COMMAND" -> Format.ACTION_COMMAND
        "COPY_TO_CLIPBOARD" -> Format.ACTION_CLIPBOARD
        else -> null
    }

}