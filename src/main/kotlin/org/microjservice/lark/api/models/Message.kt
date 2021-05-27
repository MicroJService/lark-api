package org.microjservice.lark.api.models


import java.util.*
import org.microjservice.lark.api.models.MessageRequest.I18nContent.RichTextContent.Content.TextContent
import org.microjservice.lark.api.models.MessageRequest.I18nContent.RichTextContent.Content.AtContent
import org.microjservice.lark.api.models.MessageRequest.I18nContent.RichTextContent.Content.LinkContent
import org.microjservice.lark.api.models.MessageRequest.I18nContent.RichTextContent.Content.ImgContent
import org.microjservice.lark.api.models.MessageRequest.I18nContent.RichTextContent
import java.net.URL

/**
 * Message
 *
 * @author Coder Yellow
 * @since 0.1.5
 */
interface Element {
    fun render(builder: StringBuilder, indent: String)
}

class TextElement(val text: String) : Element {
    override fun render(builder: StringBuilder, indent: String) {
        builder.append("$indent$text\n")
    }
}

@DslMarker
annotation class HtmlTagMarker

@HtmlTagMarker
abstract class Tag(val name: String) : Element {
    val children = arrayListOf<Element>()
    val attributes = hashMapOf<String, Any>()

    protected fun <T : Element> initTag(tag: T, init: T.() -> Unit): T {
        tag.init()
        children.add(tag)
        return tag
    }

    protected fun <T : Element> initTag(tag: T): T {
        children.add(tag)
        return tag
    }

    override fun render(builder: StringBuilder, indent: String) {
        builder.append("$indent<$name${renderAttributes()}>\n")
        for (c in children) {
            c.render(builder, indent + "  ")
        }
        builder.append("$indent</$name>\n")
    }

    fun toRichTextContent(): RichTextContent {
        return RichTextContent(
            (children.find { it is Title } as Title).name,
            ((children.find { it is Content } as Content).children as List<Line>)
                .map {
                    it.children.map { element ->
                        when (element) {
                            is Text -> TextContent((element.children[0] as TextElement).text)
                            is At -> AtContent(element.userid, (element.children[0] as TextElement).text)
                            is A -> LinkContent(URL(element.href), (element.children[0] as TextElement).text)
                            is Img -> ImgContent(element.imagekey, element.width, element.height)
                            else -> throw Exception("Can not handle element $element")
                        }
                    }
                }
        )
    }

    private fun renderAttributes(): String {
        val builder = StringBuilder()
        for ((attr, value) in attributes) {
            builder.append(" $attr=\"$value\"")
        }
        return builder.toString()
    }

    override fun toString(): String {
        val builder = StringBuilder()
        render(builder, "")
        return builder.toString()
    }
}

abstract class TagWithText(name: String) : Tag(name) {
    operator fun String.unaryPlus() {
        children.add(TextElement(this))
    }
}

class Title : TagWithText("title")

class Text : TagWithText("text")

class A : TagWithText("a") {
    var href: String
        get() = (attributes["href"] as String?)!!
        set(value) {
            attributes["href"] = value
        }
}

class At : TagWithText("at") {
    var userid: String
        get() = (attributes["userid"] as String?)!!
        set(value) {
            attributes["userid"] = value
        }
}

class Img : TagWithText("img") {
    var imagekey: String
        get() = (attributes["userid"] as String?)!!
        set(value) {
            attributes["userid"] = value
        }

    var width: Long
        get() = (attributes["width"] as Long?)!!
        set(value) {
            attributes["width"] = value
        }

    var height: Long
        get() = (attributes["height"] as Long?)!!
        set(value) {
            attributes["height"] = value
        }
}

class Line : TagWithText("line") {
    fun text(init: Text.() -> Unit) = initTag(Text(), init)

    fun a(href: String, init: A.() -> Unit) {
        val a = initTag(A(), init)
        a.href = href
    }

    fun at(userid: String, init: At.() -> Unit) {
        val at = initTag(At(), init)
        at.userid = userid
    }

    fun img(imagekey: String, width: Long, height: Long) {
        val img = initTag(Img())
        img.apply {
            this.imagekey = imagekey
            this.width = width
            this.height = height
        }
    }
}

class Content : TagWithText("content") {
    fun line(init: Line.() -> Unit) = initTag(Line(), init)
}


class Message : TagWithText("message") {
    var language: Locale
        get() {
            return attributes["language"] as Locale
        }
        set(value) {
            attributes["language"] = value
        }

    fun title(init: Title.() -> Unit) = initTag(Title(), init)

    fun content(init: Content.() -> Unit) = initTag(Content(), init)
}

fun message(language: Locale, init: Message.() -> Unit): Message {
    return Message().apply {
        this.language = language
        init()
    }
}


