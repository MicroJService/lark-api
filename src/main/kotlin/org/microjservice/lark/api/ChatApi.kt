package org.microjservice.lark.api

import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client
import io.reactivex.Single
import org.microjservice.lark.api.models.GroupPage
import org.microjservice.lark.api.models.BaseResponse
import org.microjservice.lark.api.models.Group

/**
 * Chat Api
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
@Client(value = "\${lark.endpoint}/chat/v4")
interface ChatApi {

    @Get("/list")
    fun list(): Single<BaseResponse<GroupPage>>

    companion object {
        @JvmStatic
        fun getChatIds(response: BaseResponse<GroupPage>): List<String> {
            return response.data.groups.map(Group::chatId)
        }
    }
}