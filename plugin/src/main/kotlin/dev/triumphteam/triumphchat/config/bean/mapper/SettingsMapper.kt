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

package dev.triumphteam.triumphchat.config.bean.mapper

import dev.triumphteam.triumphchat.config.bean.objects.BaseDisplay
import dev.triumphteam.triumphchat.config.bean.objects.FormatDisplay
import dev.triumphteam.triumphchat.config.bean.objects.MessageDisplay
import dev.triumphteam.triumphchat.config.bean.objects.elements.ClickData
import dev.triumphteam.triumphchat.func.MESSAGE_PLACEHOLDER
import me.mattstudios.config.annotations.TargetObject
import me.mattstudios.config.beanmapper.PropertyMapper
import java.util.Optional

/**
 * Setting mapper is used to make it easier to identify between interface implementations of [FormatDisplay]
 * And also to allow override default property mapper of complex properties
 */
class SettingsMapper : PropertyMapper {

    /**
     * Maps a FormatDisplay into the correct implementation
     */
    @TargetObject(FormatDisplay::class)
    fun mapFormatDisplay(path: String, data: Map<String, Any>): FormatDisplay {
        val rawText = data["text"]
        val text = if (rawText is String) rawText else ""

        val hover = createHover(data["hover"])
        val click = createClick(data["click"])

        if (MESSAGE_PLACEHOLDER in text) return MessageDisplay(text, hover, click)
        return BaseDisplay(text, hover, click)
    }

    /**
     * This is used to override the property mapper through setter, to allow the formatter to work
     */
    @TargetObject(MessageDisplay::class)
    fun mapMessageDisplay(path: String, data: Map<String, Any>): MessageDisplay {
        val rawText = data["text"]
        val text = if (rawText is String) rawText else ""
        return MessageDisplay(text, createHover(data["hover"]), createClick(data["click"]))
    }

    /**
     * Creates a hover from the map data
     */
    private fun createHover(rawHover: Any?): Optional<List<String>> {
        return if (rawHover is List<*>) {
            @Suppress("UNCHECKED_CAST")
            Optional.of<List<String>>(rawHover as List<String>)
        } else {
            Optional.empty<List<String>>()
        }
    }

    /**
     * Creates a click from the map data
     */
    private fun createClick(rawClick: Any?): Optional<ClickData> {
        return if (rawClick is Map<*, *>) {
            val type = rawClick["type"]
            val value = rawClick["value"]

            if (type == null || value == null) Optional.empty<ClickData>()
            else Optional.of(ClickData(type.toString(), value.toString()))
        } else {
            Optional.empty<ClickData>()
        }
    }

}