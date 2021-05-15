package org.microjservice.lark.api

import io.micronaut.context.annotation.Parameter
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client
import org.microjservice.lark.api.models.Message

/**
 * Message API
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
@Client(value = "\${lark.endpoint}/im/v1")
interface MessageApi {

    @Post(value = "/messages", produces = [MediaType.APPLICATION_JSON])
    fun send(@QueryValue("receive_id_type") receiveIdType: Message.ReceiveIdType, @Body message: Message)
}