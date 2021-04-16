package org.microjservice.lark.core.auth.models

import io.micronaut.core.annotation.Introspected

/**
 * Authorization response
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
@Introspected
data class AuthResponse(
    val code: String,
    val expire: Long,
    var tenantAccessToken: String?,
    var appAccessToken: String?,
){
    fun toTenantToken(): Token {
        return Token(tenantAccessToken!!,expire)
    }

    fun toAppToken(): Token {
        return Token(appAccessToken!!,expire)
    }
}
