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

package dev.triumphteam.triumphchat.init

import dev.triumphteam.core.func.Initializer
import dev.triumphteam.core.func.log
import dev.triumphteam.triumphchat.TriumphChat
import dev.triumphteam.triumphchat.func.IS_PAPER
import dev.triumphteam.triumphchat.func.log

/**
 * Object for displaying the startup message
 */
object StartupMessage : Initializer<TriumphChat> {

    /**
     * Simply logs the startup message to the console
     * Logs differently based on whether it's paper, pre 1.16.5 paper (due to console color changes), and spigot
     * @param plugin Instance of the plugin's main class
     */
    override fun initialize(plugin: TriumphChat) = with(plugin) {
        if (!IS_PAPER) {
            // TODO actually add something for spigoters lmao
            "Go die".log()
            return
        }

        // TODO make exceptions for 1.16.4-
        log {
            """
                
            <g:#FC466B:#3F5EFB>█▀▀ █░█ ▄▀█ ▀█▀ &8Version: &c${description.version}
            <g:#FC466B:#3F5EFB>█▄▄ █▀█ █▀█ ░█░ &8By: &cMatt
            
            """
        }

    }

}