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

package dev.triumphteam.triumphchat.permissions

import dev.triumphteam.triumphchat.func.MAIN_PERMISSION
import me.mattstudios.msg.base.internal.Format

object ChatPermission {
    private const val FORMAT = "${MAIN_PERMISSION}.chat.format"
    private const val COLOR = "${MAIN_PERMISSION}.chat.color"

    private const val MARKDOWN = "${FORMAT}.markdown"
    private const val LEGACY = "${FORMAT}.legacy"

    val formats = mapOf(
        // Markdown permissions
        "${MARKDOWN}.bold" to Format.BOLD,
        "${MARKDOWN}.italic" to Format.ITALIC,
        "${MARKDOWN}.underline" to Format.UNDERLINE,
        "${MARKDOWN}.strikethrough" to Format.STRIKETHROUGH,
        "${MARKDOWN}.magic" to Format.OBFUSCATED,

        // Legacy formatting permissions
        "${LEGACY}.bold" to Format.LEGACY_BOLD,
        "${LEGACY}.italic" to Format.LEGACY_ITALIC,
        "${LEGACY}.underline" to Format.LEGACY_UNDERLINE,
        "${LEGACY}.strikethrough" to Format.LEGACY_STRIKETHROUGH,
        "${LEGACY}.magic" to Format.LEGACY_OBFUSCATED,

        // Color related permissions
        "${COLOR}.color" to Format.COLOR,
        "${COLOR}.hex" to Format.HEX,
        "${COLOR}.gradient" to Format.GRADIENT,
        "${COLOR}.rainbow" to Format.RAINBOW
    )

}