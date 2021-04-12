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

package dev.triumphteam.triumphchat.locale

import me.mattstudios.config.SettingsHolder
import me.mattstudios.config.annotations.Path
import me.mattstudios.config.properties.Property
import me.mattstudios.config.properties.types.PropertyType

object Message : SettingsHolder {

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