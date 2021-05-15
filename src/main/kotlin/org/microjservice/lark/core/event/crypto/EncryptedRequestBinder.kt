package org.microjservice.lark.core.event.crypto

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import io.micronaut.core.bind.ArgumentBinder
import io.micronaut.core.convert.ArgumentConversionContext
import io.micronaut.core.reflect.ClassUtils
import io.micronaut.http.HttpRequest
import io.micronaut.http.bind.binders.AnnotatedRequestArgumentBinder
import io.micronaut.http.server.exceptions.HttpServerException
import io.micronaut.jackson.serialize.JacksonObjectSerializer
import jakarta.inject.Singleton
import org.slf4j.Logger
import java.util.*

/**
 * Lark Encrypted Request Binder
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
@Singleton
class EncryptedRequestBinder(
    private val objectSerializer: JacksonObjectSerializer,
    private val objectMapper: ObjectMapper,
    private val notifyDataDecrypter: NotifyDataDecrypter,
) : AnnotatedRequestArgumentBinder<EncryptedRequest, Any> {
    private val logger: Logger = ClassUtils.REFLECTION_LOGGER

    override fun bind(
        context: ArgumentConversionContext<Any>?,
        source: HttpRequest<*>?
    ): ArgumentBinder.BindingResult<Any> {

        return source!!.getBody(EncryptedEventRequest::class.java)
            .map {
                val type =
                    (context ?: throw HttpServerException("ArgumentConversionContext can not be null")).argument.type
                logger.info("Receive $it")
                val decryptedData = notifyDataDecrypter.decrypt(it.encrypt).toByteArray()
                ArgumentBinder.BindingResult {
                    if (type.equals(JsonNode::class.java))
                        Optional.of(objectMapper.readTree(decryptedData))
                    else
                        objectSerializer.deserialize(
                            decryptedData,
                            type
                        )
                }
            }
            .orElseGet {
                ArgumentBinder.BindingResult.UNSATISFIED
            }
    }

    override fun getAnnotationType(): Class<EncryptedRequest> {
        return EncryptedRequest::class.java
    }

}