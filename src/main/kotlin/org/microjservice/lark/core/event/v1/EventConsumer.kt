package org.microjservice.lark.core.event.v1

import org.microjservice.lark.core.event.v2.model.EventRequest

/**
 * Event Comsumer
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
interface EventConsumer<T> {
    val eventType: Class<T>

    fun handleEvent(event: T)
}