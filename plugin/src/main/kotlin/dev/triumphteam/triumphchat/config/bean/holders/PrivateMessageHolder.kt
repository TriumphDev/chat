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

package dev.triumphteam.triumphchat.config.bean.holders

import dev.triumphteam.triumphchat.config.bean.objects.elements.SubNotificationSettings
import dev.triumphteam.triumphchat.func.MESSAGE_PLACEHOLDER
import me.mattstudios.config.annotations.Comment
import me.mattstudios.config.annotations.Name
import java.util.Optional

/**
 * Holds the settings for the private messages
 */
data class PrivateMessageHolder(
    @Comment("List of formats to use for the sender.")
    @Name("sender-formats")
    var senderFormats: MutableSet<String> = mutableSetOf("default-pm-sender"),

    @Comment("List of formats to use for the recipient.")
    @Name("recipient-formats")
    var recipientFormats: MutableSet<String> = mutableSetOf("default-pm-recipient"),

    var notification: SubNotificationSettings = SubNotificationSettings(optionalActions = Optional.of(listOf("[actionbar] &cYou have received a private message!"))),

    @Comment("Single format to use for the social spy.")
    @Name("social-spy-format")
    var socialSpyFormat: String = "&8[&cSpy&8] &f%sender_player_name% &e-> &f%recipient_player_name%&7: &f$MESSAGE_PLACEHOLDER"
)