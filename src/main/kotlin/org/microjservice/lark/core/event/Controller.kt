package org.microjservice.lark.core.event

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import io.micronaut.core.reflect.ClassUtils
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Error
import io.micronaut.http.annotation.Post
import io.micronaut.http.hateoas.JsonError
import io.micronaut.http.hateoas.Link
import io.micronaut.http.server.exceptions.HttpServerException
import org.microjservice.lark.core.event.crypto.EncryptedRequest
import org.microjservice.lark.core.event.crypto.NotifyDataDecrypter
import org.microjservice.lark.core.event.model.*
import org.microjservice.lark.core.event.v1.EventService
import org.slf4j.Logger

import org.microjservice.lark.core.event.v1.model.BaseEventRequest.Type.URL_VERIFICATION
import org.microjservice.lark.core.event.v2.model.EventRequest
import org.microjservice.lark.core.event.v1.model.BaseEventRequest


/**
 * API for callback event
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
@Controller
class Controller(
    private val notifyDataDecrypter: NotifyDataDecrypter,
    private val objectMapper: ObjectMapper,
    private val eventService: EventService,
    private val eventServiceV2: org.microjservice.lark.core.event.v2.EventService
) {
    private val logger: Logger = ClassUtils.REFLECTION_LOGGER

    companion object {
        private const val SUCCESS: String = "SUCCESS"
    }

    @Error
    fun jsonError(request: HttpRequest<*>, e: JsonParseException): HttpResponse<JsonError> {
        e.printStackTrace()
        val error = JsonError("Invalid JSON: ${e.message}")
            .link(Link.SELF, Link.of(request.uri))

        return HttpResponse.status<JsonError>(HttpStatus.BAD_REQUEST, "Fix Your JSON")
            .body(error)
    }



    @Post("/lark/event", produces = [MediaType.APPLICATION_JSON])
    fun event( @EncryptedRequest   eventRequest: JsonNode): Any {
        logger.info("Receive {}", eventRequest)
        if(URL_VERIFICATION.typeName == eventRequest.get(BaseEventRequest::type.name)?.asText()){
            return eventRequest
        }
        return when (eventRequest.get(BaseEventRequest::type.name)) {
            null -> when(eventRequest.get(EventRequest<Any>::schema.name).asText()){
                EventRequest.SCHEMA.VERSION2.value -> eventServiceV2.dispatch(eventRequest)
                else -> throw HttpServerException("Unknown event type!")
            }
            else -> {
                eventService.dispatch(eventRequest)
                HttpResponse.ok(SUCCESS)
            }

        }
    }

}