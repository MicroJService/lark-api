package org.microjservice.lark.api

import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client
import org.microjservice.lark.api.models.BaseResponse
import org.microjservice.lark.api.models.BotInfo
import org.microjservice.lark.api.models.BotResponse

/**
 * Bot API
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
@Client(value = "\${lark.endpoint}/bot/v3")
interface BotApi {
    @Get("/info")
    fun info(): BotResponse
}