package me.mattstudios.triumphchat.config.settings

import me.mattstudios.config.SettingsHolder
import me.mattstudios.config.annotations.Comment
import me.mattstudios.config.annotations.Path
import me.mattstudios.config.properties.Property
import me.mattstudios.triumphchat.config.bean.objects.PlaceholderDisplay
import me.mattstudios.triumphchat.config.bean.objects.elements.MentionDisplay
import me.mattstudios.triumphchat.config.bean.objects.elements.SoundData
import java.util.Optional

object MentionSettings : SettingsHolder {


    @Comment(
        ""
    )
    @Path("everyone")
    val EVERYONE = Property.create(
        MentionDisplay(
            true,
            PlaceholderDisplay("&c**@Everyone**"),
            SoundData(),
            Optional.of("%player% has mentioned everyone")
        )
    )

    @Comment(
        ""
    )
    @Path("group")
    val GROUP = Property.create(
        MentionDisplay(
            true,
            PlaceholderDisplay("&d**@%group%**"),
            SoundData(),
            Optional.of("%player% has mentioned the group %group%")
        )
    )

    @Comment(
        ""
    )
    @Path("player")
    val PLAYER = Property.create(
        MentionDisplay(
            true,
            PlaceholderDisplay("&a__%username%__"),
            SoundData(),
            Optional.of("%player% has mentioned you")
        )
    )

}