package org.microjservice.lark.api.v3

import io.micronaut.http.HttpHeaders
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Consumes
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Header
import io.micronaut.http.annotation.RequestBean
import io.micronaut.http.client.annotation.Client
import io.reactivex.Single
import org.microjservice.lark.api.models.BaseResponse
import org.microjservice.lark.api.models.PageQuery
import org.microjservice.lark.api.v3.models.DepartmentPage
import org.microjservice.lark.api.v3.models.DepartmentQuery

/**
 *
 *
 * @author Coder Yellow
 * @since 0.1.5
 */
@Client("\${lark.endpoint}/contact/v3")
interface DepartmentApi {

    @Get("/departments")
    @Header(name = HttpHeaders.USER_AGENT,value = "micronaut")
    @Consumes(MediaType.APPLICATION_JSON)
    fun get(
        @RequestBean departmentQuery: DepartmentQuery?,
        @RequestBean pageQuery: PageQuery?
    ): Single<BaseResponse<DepartmentPage>>

}