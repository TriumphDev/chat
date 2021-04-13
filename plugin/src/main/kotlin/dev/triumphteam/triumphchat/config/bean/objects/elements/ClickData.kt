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

package dev.triumphteam.triumphchat.config.bean.objects.elements

import dev.triumphteam.triumphchat.api.ChatUser
import dev.triumphteam.triumphchat.func.parsePAPI
import me.mattstudios.msg.base.internal.Format
import me.mattstudios.msg.base.internal.action.ClickMessageAction

/**
 * Holds the click data, also makes sure the values are correct, like adding `/` to commands
 */
data class ClickData(
    var type: String? = null,
    var value: String? = null
) {

    val isNotEmpty = type != null && value != null
    private val action = selectAction()

    // Adds `/` to commands and makes it not nullable
    private val finalValue = when {
        value?.startsWith('/') == false && action == Format.ACTION_COMMAND -> "/$value"
        else -> if (value == null) "" else "$value"
    }

    fun toClick(sender: ChatUser? = null, recipient: ChatUser? = null): ClickMessageAction {
        return ClickMessageAction(action, finalValue.parsePAPI(sender, recipient))
    }

    /**
     * Selects the correct click action
     */
    private fun selectAction() = when (type?.toUpperCase()) {
        "SUGGEST_COMMAND" -> Format.ACTION_SUGGEST
        "COPY_TO_CLIPBOARD" -> Format.ACTION_CLIPBOARD
        else -> Format.ACTION_COMMAND
    }

}