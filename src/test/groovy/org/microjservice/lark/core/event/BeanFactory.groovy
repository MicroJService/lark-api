package org.microjservice.lark.core.event

import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import jakarta.inject.Singleton
import org.microjservice.lark.core.event.v2.EventConsumer

/**
 * ${description}*
 * @author Coder Yellow
 * @since ${version}
 */
@Factory
class BeanFactory {
    @Singleton
    MessageEventHandler eventConsumer(){
        return new MessageEventHandler()
    }
}
