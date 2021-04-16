package org.microjservice.lark

import io.micronaut.context.ApplicationContext
import org.microjservice.lark.api.CardMessageApi
import org.microjservice.lark.api.ChatApi
import org.microjservice.lark.core.auth.TokenManager
import org.microjservice.lark.core.auth.models.Credential
import javax.inject.Singleton

/**
 * Include all the Lark suite server side APIs
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
@Singleton
class LarkClient(
    val cardMessageApi: CardMessageApi,
    val chatApi: ChatApi,
) {

    class Builder {
        var credential: Credential? = null
        var endpoint: String? = null

        fun withCredential(credential: Credential) = apply { this.credential = credential }
        fun withEndpoint(endpoint: String) = apply { this.endpoint = endpoint }

        fun build(): LarkClient {
            val context = ApplicationContext.run()
            return context.getBean(LarkClient::class.java)
        }
    }

}