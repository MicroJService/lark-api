package org.microjservice.lark.core.event.v1

import com.fasterxml.jackson.databind.JsonNode
import io.micronaut.http.server.exceptions.HttpServerException
import io.micronaut.jackson.serialize.JacksonObjectSerializer
import jakarta.inject.Singleton
import org.microjservice.lark.core.event.model.*
import org.microjservice.lark.core.event.v1.model.BaseEvent.Type.*
import org.microjservice.lark.core.event.v2.model.EventRequest
import org.microjservice.lark.core.event.v1.model.BaseEvent
import org.microjservice.lark.core.event.v1.model.MessageEvent
import java.util.*

/**
 * Event Service
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
@Singleton
class EventService(
    private val objectSerializer: JacksonObjectSerializer,
    private val consumers: List<EventConsumer<Any>>,
) {

    private val eventTypeConventions = mapOf(
        MESSAGE.typeName to MessageEvent::class.java
    )

    fun dispatch(eventRequest: JsonNode) {
        val event = getEvent(eventRequest)
        consumers
            .filter { it.eventType.isInstance(event::class.java) }
            .forEach { it.handleEvent(event) }
    }

    private fun getEvent(eventRequest: JsonNode): BaseEvent {
        val event = eventRequest.get(EventRequest<Any>::event.name)
        val type = event.get(BaseEvent::type.name).asText()

        return objectSerializer.deserialize(
            event.toString().toByteArray(),
            eventTypeConventions[type]
        )
            .orElseThrow { HttpServerException("Empty event!") }
    }
}