package org.microjservice.lark.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import io.micronaut.core.annotation.Introspected

/**
 * CardMessage
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
@Introspected
data class CardMessage(
    val chatId: String,
    val msgType: String,
    val rootId: String?,
    val updateMulti: Boolean?,
    val card: Card,
)

@Introspected
data class Card(
    val config: Config,
    val header: Header,
    val elements: List<Module>,
) {
    data class Config(
        val wideScreenMode: Boolean?,
        val enableForward: Boolean?,
    )

    data class Header(
        val title: Text,
        val template: Template,
    ) {
        data class Title(
            val tag: String,
            val content: String?,
            val i18n: I18N?,

        ) {
            data class I18N(
                val zhCn: String,
                val enUs: String,
                val jaJp: String,
            )
        }
        enum class Template {
            BLUE, WATHET, Turquoise, GREEN, YELLOW,
            ORANGE, RED, CARMINE, VIOLET
        }

    }

    open class Module(val tag: Tag) {
        @JsonNaming(value = PropertyNamingStrategies.LowerCaseStrategy::class)
        enum class Tag {
            @JsonProperty("div") DIV,
            HR, IMG, ACTION, NOTE,
        }

        class DivModule(
            val text: Text?,
            val fields: List<Field>?,
            val extra: Element?,
        ) : Module(Tag.DIV)

    }

    open class Element(val tag: Tag) {
        enum class Tag {
            IMG, BUTTON, SELECT_STATIC, SELECT_PERSON, OVERFLOW,
        }

        data class DivElement(
            val imgKey: String,
            val alt: Text,
            val preview: Boolean,
        ) : Element(Tag.IMG)
    }

    class Text(
        val tag: Tag,
        val content: String,
        val lines: Int?,
        val i18n: I18N?,
    ) {
        @JsonNaming(value = PropertyNamingStrategies.LowerCaseStrategy::class)
        enum class Tag {
            @JsonProperty("plain_text")
            PLAIN_TEXT,
            @JsonProperty("lark_md")
            LARK_MD,
        }

        data class I18N(
            val zhCn: String,
            val enUs: String,
            val jaJp: String,
        )

        enum class Template {
            BLUE, WATHET, Turquoise, GREEN, YELLOW,
            ORANGE, RED, CARMINE, VIOLET
        }
    }

    class Field(
        val isShort: Boolean,
        val text: Text,
    )
}