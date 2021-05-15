package org.microjservice.lark.core.event.v2.model

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

/**
 * EventRequest Deserializer
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
class EventRequestDeserializer : JsonDeserializer<EventRequest<Any>>() {

    companion object {
        private const val EVENT_TYPE_FIELD_NAME: String = "event_type"
    }

    private val eventTypeConventions = mapOf(
        EventRequest.Header.EventType.MESSAGE.value to MessageEvent::class.java,
        "im.chat.member.user.added_v1" to ChatMemberAddedEvent::class.java
    )

    private val objectMapper: ObjectMapper = ObjectMapper()
        .registerKotlinModule()
        .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)

    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): EventRequest<Any> {
        val jsonNode: JsonNode = p!!.readValueAsTree()
        val eventNode = jsonNode.get(EventRequest<Any>::event.name)
        val type = jsonNode
            .get(EventRequest<Any>::header.name)
            .get(EVENT_TYPE_FIELD_NAME)
            .asText()

        val event = objectMapper.readValue(
            eventNode.toString(),
            eventTypeConventions[type]
        )

        val eventRequest = objectMapper.convertValue(jsonNode, EventRequest::class.java)
        eventRequest.event = event

        @Suppress("UNCHECKED_CAST")
        return eventRequest as EventRequest<Any>
    }

}