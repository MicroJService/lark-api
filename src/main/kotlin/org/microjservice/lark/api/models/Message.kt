package org.microjservice.lark.api.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.micronaut.core.annotation.Introspected
import java.net.URL

/**
 * Message
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
@Introspected
data class Message(
    val receiveId: String,
    @JsonIgnore
    val contentObject: I18nContent,
    val msgType: MessageType
) {
    @JvmField
    @JsonIgnore
    val objectMapper: ObjectMapper = ObjectMapper()
        .registerKotlinModule()
        .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)

    val content: String = objectMapper.writeValueAsString(contentObject)


    enum class MessageType {
        @JsonProperty("text")
        TEXT,
        @JsonProperty("post")
        POST
    }

    enum class ReceiveIdType {
        @JsonProperty("open_id")
        OPEN_ID,

        @JsonProperty("user_id")
        USER_ID,

        @JsonProperty("union_id")
        UNION_ID,

        @JsonProperty("email")
        EMAIL,

        @JsonProperty("chat_id")
        CHAT_ID;

        override fun toString(): String {
            return name.toLowerCase()
        }
    }

    class I18nContent(
        val zhCn: RichTextContent?,
        val enUs: RichTextContent?,
    ) {
        class RichTextContent(
            val title: String,
            val content: List<List<Content>>,
        ) {
            open class Content(
                val tag: Tag
            ) {
                enum class Tag {
                    @JsonProperty("text")
                    TEXT,

                    @JsonProperty("a")
                    A,

                    @JsonProperty("at")
                    AT,

                    @JsonProperty("img")
                    IMG
                }

                class TextContent(
                    val text: String,
                ) : Content(Tag.TEXT)

                class LinkContent(
                    val href: URL,
                    val text: String,
                ) : Content(Tag.A)

                class AtContent(
                    val userId: String,
                    val userName: String?
                ) : Content(Tag.AT)

                class ImgContent(
                    val imageKey: String,
                    val userId: String,
                    val width: Int,
                    val height: Int,
                ) : Content(Tag.IMG)

            }

        }
    }

}
