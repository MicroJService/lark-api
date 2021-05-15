package org.microjservice.lark.core.auth

import io.micronaut.context.BeanProvider
import jakarta.inject.Singleton
import org.microjservice.lark.core.auth.models.Credential
import org.microjservice.lark.core.auth.models.Credential.CredentialType
import org.microjservice.lark.core.auth.models.Token

@Singleton
class AuthService(private val authorizationApiProvider: BeanProvider<AuthorizationApi>) {
    fun getToken(credential: Credential): Token {
        val authorizationApi = authorizationApiProvider.get()
        return when (credential.credentialType) {
            CredentialType.INTERNAL_APP -> authorizationApi.internalAppAccessToken(credential).toAppToken()

            CredentialType.APP -> authorizationApi.appAccessToken(credential).toAppToken()

            CredentialType.INTERNAL_TENANT -> authorizationApi.internalTenantAccessToken(credential).toTenantToken()

            CredentialType.TENANT -> authorizationApi.tenantAccessToken(credential).toTenantToken()
        }
    }

}
