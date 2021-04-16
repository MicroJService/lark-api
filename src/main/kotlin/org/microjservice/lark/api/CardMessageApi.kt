package org.microjservice.lark.api

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client
import org.microjservice.lark.api.models.BaseResponse
import org.microjservice.lark.api.models.CardMessage
import org.microjservice.lark.api.models.ResponseMessage

/**
 * Card Message Api
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
@Client(value ="\${lark.endpoint}/message/v4")
interface CardMessageApi {
    @Post("/send")
    fun send(@Body cardMessage: CardMessage): BaseResponse<ResponseMessage>
}