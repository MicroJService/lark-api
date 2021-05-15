package org.microjservice.lark.core.event.v2.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Message Event
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
class MessageEvent(
    val sender: Sender,
    val message: Message
) {

    class Sender(
        val senderId: Id,
        val senderType: SenderType,
        val tenantKey: String,
    ) {
        enum class SenderType(val value: String) {
            @JsonProperty("user")
            USER("user");

            override fun toString(): String {
                return name.toLowerCase()
            }
        }
    }

    class Message(
        val messageId: String,
        val rootId: String?,
        val parentId: String?,
        val createTime: String,
        val chatId: String,
        val chatType: String,
        val messageType: String,
        val content: String,
        val mentions: List<Mention>,
    )

    class Mention(
        val key: String,
        val id: Id,
        val name: String,
        val tenantKey: String,
    )

    class Id(
        val unionId: String,
        val userId: String,
        val openId: String,
    )
}