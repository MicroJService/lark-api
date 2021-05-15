package org.microjservice.lark.core.event.v2

import org.microjservice.lark.core.event.v2.model.EventRequest

/**
 * Event Handler
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
interface EventConsumer<T> {
    val eventType: Class<T>

    fun handleEvent(eventRequest: EventRequest<T>)
}