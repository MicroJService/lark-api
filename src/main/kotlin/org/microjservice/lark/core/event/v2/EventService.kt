package org.microjservice.lark.core.event.v2

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import io.micronaut.jackson.serialize.JacksonObjectSerializer
import jakarta.inject.Singleton
import org.microjservice.lark.core.event.v2.model.EventRequest


/**
 * Event Service
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
@Singleton
class EventService(
    private val objectMapper: ObjectMapper,
    private val objectSerializer: JacksonObjectSerializer,
    private val consumers: List<EventConsumer<Any>>,

) {

    fun dispatch(eventRequest: JsonNode) {
        val event = objectMapper.convertValue(eventRequest, EventRequest::class.java)
        consumers
            .filter { it.eventType == event.event!!::class.java }

            .forEach {
                @Suppress("UNCHECKED_CAST")
                it.handleEvent( event as EventRequest<Any>)
            }
    }

}