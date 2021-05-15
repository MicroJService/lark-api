package org.microjservice.lark.core.event.v2.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty

//@JsonDeserialize(using = EventRequestDeserializer::class)
data class EventRequest<T>(
    val schema: SCHEMA,
    val header: Header,

    ) {
    @JsonIgnore
    var event: Any? = null

    enum class SCHEMA(val value: String) {
        @JsonProperty("2.0")
        VERSION2("2.0")
    }

    data class Header(
        val eventId: String,
        val eventType: EventType,
        val token: String,
        val createTime: String,
        val tenantKey: String,
        val appId: String,
    ) {
        enum class EventType(val value: String) {
            @JsonProperty("im.message.receive_v1")
            MESSAGE("im.message.receive_v1")
        }
    }

}
