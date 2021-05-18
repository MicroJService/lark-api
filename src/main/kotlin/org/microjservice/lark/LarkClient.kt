package org.microjservice.lark

//import jakarta.inject.Singleton
import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.runtime.server.EmbeddedServer
import org.microjservice.lark.api.BotApi
import org.microjservice.lark.api.CardMessageApi
import org.microjservice.lark.api.ChatApi
import org.microjservice.lark.api.MessageApi
import org.microjservice.lark.api.v1.UserApi
import org.microjservice.lark.core.auth.AuthorizationApi
import org.microjservice.lark.core.auth.models.Credential
import org.microjservice.lark.core.event.v2.EventConsumer
import javax.inject.Singleton

/**
 * Include all the Lark suite server side APIs
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
@Singleton
class LarkClient(
    val authorizationApi: AuthorizationApi,
    val chatApi: ChatApi,
    val cardMessageApi: CardMessageApi,
    val messageApi: MessageApi,
    val botApi: BotApi,
    val userApi: UserApi,
) {
    var context: ApplicationContext? = null


    class Builder {

        companion object {
            const val APP_ID_PROPERTY_NAME = "lark.app-id"
            const val APP_SECRETE_PROPERTY_NAME = "lark.app-secret"
            const val AUTHORIZATION_TYPE_PROPERTY_NAME = "lark.authorization-type"
            const val ENDPOINT_PROPERTY_NAME = "lark.endpoint"
            const val ENCRYPT_KEY_PROPERTY_NAME = "encrypt-key"
        }

        private var credential: Credential? = null

        private var endpoint: String? = null

        private  var packages: Array<out String> = emptyArray()

        private var encryptKey: String? = null

        private var eventHandlers: MutableList<EventConsumer<Any>> = mutableListOf()

        fun withCredential(credential: Credential) = apply { this.credential = credential }

        fun withEndpoint(endpoint: String) = apply { this.endpoint = endpoint }

        fun withEventHandler(eventHandler: EventConsumer<Any>) = apply { this.eventHandlers.add(eventHandler) }

        fun withPackages(vararg packages: String) = apply { this.packages = packages }

        fun withEncryptKey(encryptKey: String) = apply { this.encryptKey = encryptKey }


        fun build(): LarkClient {
            val properties = mutableMapOf<String, Any?>().apply {
                credential?.let {
                    put(APP_ID_PROPERTY_NAME, credential?.appId)
                    put(APP_SECRETE_PROPERTY_NAME, credential?.appSecret)
                    put(AUTHORIZATION_TYPE_PROPERTY_NAME, credential?.credentialType)
                }

                endpoint?.let { put(ENDPOINT_PROPERTY_NAME, endpoint) }
                encryptKey?.let { put(ENCRYPT_KEY_PROPERTY_NAME, encryptKey) }
            }
            val context = ApplicationContext
                .builder()
                .properties(properties)
                .singletons(*eventHandlers.toTypedArray())
                .packages(*packages)
                .start()

            val larkClient = context.getBean(LarkClient::class.java)
            larkClient.context = context
            return larkClient
        }
    }
}