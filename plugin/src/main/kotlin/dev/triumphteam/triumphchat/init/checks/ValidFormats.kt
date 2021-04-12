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

package dev.triumphteam.triumphchat.init.checks

import dev.triumphteam.core.func.Checker
import dev.triumphteam.core.func.log
import dev.triumphteam.triumphchat.TriumphChat
import dev.triumphteam.triumphchat.config.FormatsConfig
import dev.triumphteam.triumphchat.config.MainConfig
import dev.triumphteam.triumphchat.config.settings.Setting

/**
 * Object for validating formats from the format.yml file
 */
object ValidFormats : Checker<TriumphChat> {

    /**
     * Checks if the formats in the config.yml are registered in the formats.yml
     * @param plugin Instance of the plugin's main class
     */
    override fun check(plugin: TriumphChat): Boolean = with(plugin) {
        val formats = config<FormatsConfig>().getFormats()

        val invalidChatFormats = config<MainConfig>()[Setting.CHAT_FORMATS].formats.filter { it !in formats.keys }
        if (invalidChatFormats.isNotEmpty()) {
            "&6The following &7chat &6formats: &c${invalidChatFormats.joinToString("&6, &c")} &6are not registered in the &cformats.yml &6and will be ignored!".log()
        }

        val invalidSenderFormats =
            config<MainConfig>()[Setting.PRIVATE_MESSAGES].senderFormats.filter { it !in formats.keys }
        if (invalidSenderFormats.isNotEmpty()) {
            "&6The following &7sender &6formats: &c${invalidSenderFormats.joinToString("&6, &c")} &6are not registered in the &cformats.yml &6and will be ignored!".log()
        }

        val invalidRecipientFormats =
            config<MainConfig>()[Setting.PRIVATE_MESSAGES].recipientFormats.filter { it !in formats.keys }
        if (invalidRecipientFormats.isNotEmpty()) {
            "&6The following &7recipient &6formats: &c${invalidRecipientFormats.joinToString("&6, &c")} &6are not registered in the &cformats.yml &6and will be ignored!".log()
        }

        return true
    }

}