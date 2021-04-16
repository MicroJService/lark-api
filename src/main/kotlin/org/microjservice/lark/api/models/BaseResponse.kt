package org.microjservice.lark.api.models

import io.micronaut.core.annotation.Introspected

/**
 * List Response
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
@Introspected
open class BaseResponse<T>(
    val code: Int,
    val msg: String,
    val data:T,
)