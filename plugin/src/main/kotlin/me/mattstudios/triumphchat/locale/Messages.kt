package me.mattstudios.triumphchat.locale

import me.mattstudios.config.SettingsHolder
import me.mattstudios.config.annotations.Path
import me.mattstudios.config.properties.Property
import me.mattstudios.config.properties.types.PropertyType

object Messages : SettingsHolder {

    @Path("command.general.wrong-usage")
    val COMMAND_WRONG_USAGE =
        Property.create(PropertyType.STRING) { DefaultMessage.COMMAND_WRONG_USAGE.getMessage() }

    @Path("command.general.no-permission")
    val COMMAND_NO_PERMISSION =
        Property.create(PropertyType.STRING) { DefaultMessage.COMMAND_NO_PERMISSION.getMessage() }

    @Path("command.message.recipient-no-exist")
    val MESSAGE_RECIPIENT_NO_EXIST =
        Property.create(PropertyType.STRING) { DefaultMessage.MESSAGE_RECIPIENT_NO_EXIST.getMessage() }

    @Path("command.reply.no-reply-target")
    val REPLY_NO_REPLY_TARGET =
        Property.create(PropertyType.STRING) { DefaultMessage.REPLY_NO_REPLY_TARGET.getMessage() }

}