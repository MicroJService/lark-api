package org.microjservice.lark.core.auth

import io.micronaut.http.client.HttpClientConfiguration
import javax.inject.Named
import javax.inject.Singleton

/**
 * Auth HttpClientConfiguration
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
//@Named(value ="\${lark.endpoint}/auth")
@Singleton
class AuthHttpClientConfiguration: HttpClientConfiguration() {
    override fun getConnectionPoolConfiguration(): ConnectionPoolConfiguration {
        TODO("Not yet implemented")
    }

}