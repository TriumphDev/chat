package me.mattstudios.triumphchat.config.bean.objects

data class Click(
    var type: String? = null,
    var value: String? = null
) {
    val isEmpty = type == null || value == null
}