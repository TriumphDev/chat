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

package dev.triumphteam.triumphchat.extensions.parser

import dev.triumphteam.triumphchat.extensions.parser.nodes.PingNode
import me.mattstudios.msg.commonmark.internal.inline.ParsedInline
import me.mattstudios.msg.commonmark.internal.inline.Scanner
import me.mattstudios.msg.commonmark.internal.inline.triumph.TriggerProcessor

class PingParser : TriggerProcessor {

    override fun getTriggerCharacter() = '@'

    override fun parse(scanner: Scanner): ParsedInline? {
        // Skips the `@`
        scanner.next()

        // Gets the start position
        val start = scanner.position()

        // The character count
        var characters = 0
        while (scanner.hasNext()) {
            scanner.next()

            val char = scanner.peek()

            // Checks if it's not a letter, space, or underline
            if (char == ' ' || (!Character.isLetter(char) && char != '_')) {
                val literal = scanner.textBetween(start, scanner.position()).toString()
                return ParsedInline.of(PingNode(literal), scanner.position())
            }

            if (characters > 16) break

            characters++
        }

        // No ping found
        return ParsedInline.none()
    }

}