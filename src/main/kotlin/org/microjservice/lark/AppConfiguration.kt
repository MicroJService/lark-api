package org.microjservice.lark

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.context.event.BeanCreatedEvent
import io.micronaut.context.event.BeanCreatedEventListener
import jakarta.inject.Singleton
import org.microjservice.lark.core.event.v2.EventConsumer
import org.microjservice.lark.core.event.v2.model.EventRequest
import org.microjservice.lark.core.event.v2.model.EventRequestDeserializer
import org.microjservice.lark.core.event.v2.model.MessageEvent
import org.slf4j.LoggerFactory


/**
 * Configuration for Lark suite.
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
@Factory
class AppConfiguration {


    @Bean
    fun messageEventHandlerV2(): EventConsumer<MessageEvent> {
        return object: EventConsumer<MessageEvent> {

            val logger = LoggerFactory.getLogger(javaClass)

            override val eventType: Class<MessageEvent>
                get() = MessageEvent::class.java

            override fun handleEvent(eventRequest: EventRequest<MessageEvent>) {
                logger.info("Receive $eventRequest")
            }
        }
    }

    @Singleton
    internal class ObjectMapperBeanEventListener : BeanCreatedEventListener<ObjectMapper?> {
        override fun onCreated(event: BeanCreatedEvent<ObjectMapper?>): ObjectMapper {
            val mapper: ObjectMapper = event.bean ?: ObjectMapper()
            mapper.registerModule(object : SimpleModule() {
                init {
                    addDeserializer(EventRequest::class.java, EventRequestDeserializer())
                }
            })
            return mapper
        }
    }
}