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

package dev.triumphteam.triumphchat.init.command

import dev.triumphteam.core.func.Initializer
import dev.triumphteam.triumphchat.TriumphChat
import dev.triumphteam.triumphchat.func.sendTo
import dev.triumphteam.triumphchat.locale.Message

/**
 *  Initializer object for anything to fo with command messages
 */
object CommandMessages : Initializer<TriumphChat> {

    /**
     * Overrides the framework's messages and adds custom ones too
     * @param plugin Instance of the plugin's main class
     */
    override fun initialize(plugin: TriumphChat) = with(plugin) {
        commands {
            message("cmd.no.permission") { locale[Message.COMMAND_NO_PERMISSION].sendTo(it) }
            message("cmd.wrong.usage") { locale[Message.COMMAND_WRONG_USAGE].sendTo(it) }
        }
    }

}