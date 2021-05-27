package org.microjservice.lark.api.v1

import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client
import io.reactivex.Single
import org.microjservice.lark.api.models.BaseResponse
import org.microjservice.lark.api.v1.models.BatchUser
import java.util.*

/**
 * User Api
 *
 * @author Coder Yellow
 * @since 0.1.3
 */
@Client("\${lark.endpoint}/user/v1")
interface UserApi {

    @Get("/batch_get_id{?emails*,mobiles*}")
    fun batchGetId(
        @QueryValue emails: List<String>?,
        @QueryValue mobiles: List<String>?,
    ): Single<BaseResponse<BatchUser>>

    companion object {

        @JvmStatic
        fun getOpenIds(userResponse: BaseResponse<BatchUser>): Optional<List<String>> {
            return Optional.ofNullable(
                userResponse.data.emailUsers?.values?.map(BatchUser.UserId::openId)
            )
        }
    }

}