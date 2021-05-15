package org.microjservice.lark.core.event.v1.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Base Event
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
open class BaseEvent(
    val type: Type,
) {
    enum class Type(val typeName: String) {
        @JsonProperty("message")
        MESSAGE("message"),
        REMOVE_USER_FROM_CHAT("remove_user_from_chat")
    }
}