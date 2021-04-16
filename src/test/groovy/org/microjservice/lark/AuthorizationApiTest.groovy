package org.microjservice.lark


import io.micronaut.test.extensions.spock.annotation.MicronautTest
import org.junit.platform.commons.util.StringUtils
import org.microjservice.lark.core.auth.AuthorizationApi
import org.microjservice.lark.core.auth.models.Credential

import spock.lang.Specification

import javax.inject.Inject

/**
 * Uni test for {@link org.microjservice.lark.core.auth.AuthorizationApi}
 * @author Coder Yellow
 * @since 0.1.0
 */
@MicronautTest
class AuthorizationApiTest extends Specification {
    @Inject
    AuthorizationApi authorizationApi

    @Inject
    Credential credential


    def "get InternalAppAccessToken with credential successfully"() {
        given:
        def authResponse = authorizationApi.internalAppAccessToken(credential)
        expect:
        StringUtils.isNotBlank(authResponse.appAccessToken)
    }

    /**
     * todo support event call back to get `app_ticket` and test this function
     */
    def "get AppAccessToken with credential successfully"() {
        given:
        def authResponse = authorizationApi.appAccessToken(credential)
        expect:
        StringUtils.isNotBlank(authResponse.appAccessToken)
    }

    def "get InternalTenantAccessToken with credential successfully"() {
        given:
        def authResponse = authorizationApi.internalTenantAccessToken(credential)
        expect:
        StringUtils.isNotBlank(authResponse.tenantAccessToken)
    }

    def "get TenantAccessToken with credential successfully"() {
        given:
        def authResponse = authorizationApi.tenantAccessToken(credential)
        expect:
        StringUtils.isNotBlank(authResponse.tenantAccessToken)
    }
}
