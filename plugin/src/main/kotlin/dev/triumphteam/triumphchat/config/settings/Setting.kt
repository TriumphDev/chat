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

package dev.triumphteam.triumphchat.config.settings

import dev.triumphteam.triumphchat.config.bean.holders.ChatHolder
import dev.triumphteam.triumphchat.config.bean.holders.MentionsHolder
import dev.triumphteam.triumphchat.config.bean.holders.NotificationHolder
import dev.triumphteam.triumphchat.config.bean.holders.PrivateMessageHolder
import dev.triumphteam.triumphchat.func.MESSAGE_PLACEHOLDER
import me.mattstudios.config.SettingsHolder
import me.mattstudios.config.annotations.Comment
import me.mattstudios.config.annotations.Path
import me.mattstudios.config.properties.Property

/**
 * Main setting holder for all the config related settings
 */
object Setting : SettingsHolder {

    @Comment(
        ""
    )
    @Path("chat")
    val CHAT_FORMATS = Property.create(ChatHolder())

    @Comment(
        "",
        "Format that will be sent to the console, use $MESSAGE_PLACEHOLDER to represent the message that'll be sent.",
        "Due to console limitations, formats and RGB are not supported here, only basic colors."
    )
    @Path("console-format")
    val CONSOLE_FORMAT = Property.create("[%vault_prefix%] %player_name% > $MESSAGE_PLACEHOLDER")

    @Comment(
        "",
        "Base settings for all the notifications.",
        "Currently only sound."
        // TODO add more
    )
    @Path("notification")
    val NOTIFICATION = Property.create(NotificationHolder())

    @Comment(
        "",
        // TODO Better comment
        "Settings customizing the mentions."
    )
    @Path("mentions")
    val MENTIONS = Property.create(MentionsHolder())

    @Comment(
        "",
        "Customize your private messages."
    )
    @Path("private-messages")
    val PRIVATE_MESSAGES = Property.create(PrivateMessageHolder())

}