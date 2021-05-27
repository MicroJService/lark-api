package org.microjservice.lark.api.im.v1

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.http.client.annotation.Client
import io.reactivex.Single
import org.microjservice.lark.api.im.v1.modles.Chat
import org.microjservice.lark.api.im.v1.modles.MemberAddResponse
import org.microjservice.lark.api.models.BaseResponse
import org.microjservice.lark.api.models.UserIdType

/**
 * Chat Api
 *
 * @author Coder Yellow
 * @since 0.1.5
 */
@Client("\${lark.endpoint}/im/v1/chats")
interface ChatApi {
    @Post
    fun create(@Body chat: Chat): Single<BaseResponse<Chat>>

    @Post("/{chatId}/members")
    @Produces(MediaType.APPLICATION_JSON)
    fun addMembers(
        @PathVariable chatId: String,
        @QueryValue memberIdType: UserIdType,
        //todo: camel-case to snake-case
        id_list: List<String>,
    ): Single<BaseResponse<MemberAddResponse>>
}