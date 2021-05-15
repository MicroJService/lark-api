package org.microjservice.lark.core.auth

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.HttpClientConfiguration
import io.micronaut.http.client.annotation.Client
import org.microjservice.lark.core.auth.models.AuthResponse
import org.microjservice.lark.core.auth.models.Credential

/**
 * API to get access token.
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
@Client(value ="\${lark.endpoint}/auth")
interface AuthorizationApi {

    /**
     * Obtain `app_access_token` from [AuthResponse] by [Credential] (internal apps).
     *
     * See [Lark suite document](https://open.larksuite.com/document/uMzMyEjLzMjMx4yMzITM/uMjN0EjLzYDNx4yM2QTM) for more details.
     */
    @Post("/v3/app_access_token/internal")
    fun internalAppAccessToken(@Body credential: Credential): AuthResponse

    /**
     * Obtain `app_access_token` from [AuthResponse] by [Credential] (ISV apps).
     *
     * See [Lark suite document](https://open.larksuite.com/document/uMzMyEjLzMjMx4yMzITM/uMjN0EjLzYDNx4yM2QTM) for more details.
     */
    @Post("/v3/app_access_token")
    fun appAccessToken(@Body credential: Credential): AuthResponse

    /**
     * Obtain `tenant_access_token` from [AuthResponse] by [Credential] (internal apps).
     *
     * see [Lark suite document](https://open.larksuite.com/document/uMzMyEjLzMjMx4yMzITM/uUjN0EjL1YDNx4SN2QTM) for more details.
     */
    @Post("/v3/tenant_access_token/internal")
    fun internalTenantAccessToken(@Body credential: Credential): AuthResponse

    /**
     * Obtain `tenant_access_token` from [AuthResponse] by [Credential] (internal apps).
     *
     * See [Lark suite document](https://open.larksuite.com/document/uMzMyEjLzMjMx4yMzITM/uYjN0EjL2YDNx4iN2QTM) for more details.
     */
    @Post("/v3/tenant_access_token")
    fun tenantAccessToken(@Body credential: Credential): AuthResponse

    @Post("/v3/app_ticket/resend")
    fun resendAppTicket(@Body credential: Credential): AuthResponse

}