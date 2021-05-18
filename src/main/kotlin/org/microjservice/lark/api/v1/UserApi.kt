package org.microjservice.lark.api.v1

import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client
import org.microjservice.lark.api.models.BaseResponse
import org.microjservice.lark.api.v1.models.BatchUser

/**
 * User Api
 *
 * @author Coder Yellow
 * @since 0.1.3
 */
@Client("\${lark.endpoint}/user/v1")
interface UserApi {

    @Get("/batch_get_id")
    fun batchGetId(@QueryValue emails: List<String>?, @QueryValue mobiles: List<String>?): BaseResponse<BatchUser>

}