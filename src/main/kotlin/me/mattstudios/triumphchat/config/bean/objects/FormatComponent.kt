package me.mattstudios.triumphchat.config.bean.objects

import java.util.Optional

interface FormatComponent {
    val text: String
    val hover: Optional<List<String>>
    val click: Optional<Click>
}