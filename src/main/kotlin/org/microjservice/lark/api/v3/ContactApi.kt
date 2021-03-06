package org.microjservice.lark.api.v3

import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client
import org.microjservice.lark.api.models.BaseResponse
import org.microjservice.lark.api.models.UserIdType
import org.microjservice.lark.api.v3.models.UserResponse

/**
 * Contact Api
 *
 * @author Coder Yellow
 * @since 0.1.4
 */
@Client("\${lark.endpoint}/contact/v3")
interface ContactApi {
    @Get("/users/{userId}")
    fun user(@PathVariable userId: String, @QueryValue("user_id_type") userIdType: UserIdType): BaseResponse<UserResponse>

    @Get("")
    fun users(@QueryValue("user_id_type") userIdType: UserIdType): BaseResponse<UserResponse>

}