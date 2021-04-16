package org.microjservice.lark.core.auth.filter

import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpRequest
import io.micronaut.http.annotation.Filter
import io.micronaut.http.filter.ClientFilterChain
import io.micronaut.http.filter.HttpClientFilter
import org.microjservice.lark.core.auth.TokenManager
import org.reactivestreams.Publisher

/**
 * Authorization filter
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
@Filter( "\${lark.auth-patterns}")
class AuthFilter(
    private val tokenManager: TokenManager
) : HttpClientFilter {
    override fun doFilter(request: MutableHttpRequest<*>, chain: ClientFilterChain): Publisher<out HttpResponse<*>> {
        return chain.proceed(request.bearerAuth(tokenManager.getAccessToken()))
    }

}