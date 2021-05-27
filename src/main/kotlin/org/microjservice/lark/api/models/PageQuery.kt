package org.microjservice.lark.api.models

import io.micronaut.core.annotation.Introspected
import io.micronaut.http.annotation.QueryValue
import javax.validation.constraints.Max

@Introspected
data class PageQuery(
    @field:QueryValue("page_size")
    @field:Max(50)
    val pageSize: Int? = 50,
    @field:QueryValue("page_token")
    val pageToken: String?,
) {

}
