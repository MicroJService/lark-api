package org.microjservice.lark.core.event

import jakarta.inject.Singleton
import org.jetbrains.annotations.NotNull
import org.microjservice.lark.core.event.v2.EventConsumer
import org.microjservice.lark.core.event.v2.model.EventRequest
import org.microjservice.lark.core.event.v2.model.MessageEvent

/**
 * ${description}*
 * @author Coder Yellow
 * @since ${version}
 */
//@Singleton
class MessageEventHandler implements EventConsumer<MessageEvent> {

    MessageEvent messageEvent

    @Override
    Class<MessageEvent> getEventType() {
        return MessageEvent.class
    }

    @Override
    void handleEvent(@NotNull EventRequest<MessageEvent> eventRequest) {
        this.messageEvent = eventRequest.event as MessageEvent
    }
}
