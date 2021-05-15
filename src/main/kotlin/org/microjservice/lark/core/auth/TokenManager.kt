package org.microjservice.lark.core.auth

import jakarta.inject.Singleton
import org.microjservice.lark.core.auth.models.Credential
import org.microjservice.lark.core.auth.models.Token

/**
 * API to get access token.
 *
 * @author Coder Yellow
 * @since 0.1.0
 */

@Singleton
class TokenManager(
    private val authService: AuthService,
    val credential: Credential,
) {

    @Volatile
    var token: Token? =null

    fun getAccessToken(): String {
        if (null == token || token!!.isExpired()) {
            refreshToken()
        }
        return token!!.accessToken
    }

    private fun refreshToken() {
        token = authService.getToken(credential)
    }
}
