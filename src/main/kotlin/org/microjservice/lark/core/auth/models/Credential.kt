package org.microjservice.lark.core.auth.models

import io.micronaut.context.annotation.Value
import javax.inject.Singleton

/**
 * Lark suite authentication credential to obtain access token.
 *
 * see [Lark suite doc](https://open.larksuite.com/document/uMzMyEjLzMjMx4yMzITM/ukjMyEjL5IjMx4SOyITM) for more details
 * @author Coder Yellow
 * @since 0.1.0
 */
@Singleton
data class Credential(
    @Value("\${lark.app-id}")
    val appId: String,
    @Value("\${lark.app-secret}")
    val appSecret: String,
    @Value("\${lark.authorization-type}")
    val credentialType: CredentialType,
) {
    var appTicket: String? = null

    constructor(
        appId: String,
        appSecret: String,
        credentialType: CredentialType,
        appTicket: String?
    ) : this(appId, appSecret, credentialType) {
        this.appTicket = appTicket
    }

    enum class CredentialType {
        INTERNAL_APP,
        APP,
        INTERNAL_TENANT,
        TENANT
    }
}