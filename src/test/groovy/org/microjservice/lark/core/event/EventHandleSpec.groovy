package org.microjservice.lark.core.event

import io.micronaut.context.annotation.Value
import io.micronaut.core.reflect.InstantiationUtils
import io.micronaut.jackson.serialize.JacksonObjectSerializer
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.jetbrains.annotations.NotNull
import org.microjservice.lark.LarkClient
import org.microjservice.lark.api.models.Message
import org.microjservice.lark.core.auth.models.Credential

import org.microjservice.lark.core.event.v2.EventConsumer
import org.microjservice.lark.core.event.v2.model.EventRequest
import org.microjservice.lark.core.event.v2.model.MessageEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spock.lang.Shared
import spock.lang.Specification
import spock.util.concurrent.PollingConditions

/**
 * EventHandleSpec
 * @author Coder Yellow
 * @since 0.1.0
 */
@MicronautTest
class EventHandleSpec extends Specification {
    @Shared
    @Value("\${lark.app-id}")
    String appId = System.getenv("LARK_APP_ID")
    @Shared
    @Value("\${lark.app-secret}")
    String appSecret = System.getenv("LARK_APP_SECRET")
    @Shared
    @Value("\${lark.endpoint}")
    String endpoint = System.getenv("LARK_ENDPOINT")
    @Inject
    Credential credential
    @Inject
    TicketManager ticketManager

    @Inject
    JacksonObjectSerializer jacksonObjectSerializer

    @Shared
//    @Inject
    MessageEventHandler messageEventHandler

    @Shared
    LarkClient larkClient
    @Shared
    Logger logger = LoggerFactory.getLogger(InstantiationUtils.class)


    def setupSpec() {
        larkClient = new LarkClient.Builder()
                .withCredential(
                        new Credential(
                                appId, appSecret, Credential.CredentialType.INTERNAL_APP)
                )
                .withEndpoint(endpoint)
                .build()
    }

    def 'should receive message event'() {
        given:
        PollingConditions conditions = new PollingConditions(delay: 1, timeout: 64)
        def chatId = larkClient.getChatApi().list().getData().getGroups().find().getChatId()

        def botInfo = larkClient.botApi.info().bot
        def openId = botInfo.openId

        messageEventHandler = larkClient.context.getBean(MessageEventHandler)

        println Integer.toHexString(System.identityHashCode(messageEventHandler))

        when:
        larkClient.messageApi.send(
                Message.ReceiveIdType.CHAT_ID,
                new Message(
                        chatId,
                        new Message.I18nContent(
                                new Message.I18nContent.RichTextContent(
                                        "Test for message event",
                                        [[new Message.I18nContent.RichTextContent.Content.AtContent(openId, "Test?")]]
                                ),
                                new Message.I18nContent.RichTextContent(
                                        "Test for message event",
                                        [[new Message.I18nContent.RichTextContent.Content.TextContent("test en_us")]]
                                ),
                        )
                        ,
                        Message.MessageType.POST
                )
        )

        then:
        conditions.eventually {
            logger.info("Receive message {}", messageEventHandler.messageEvent)
            null != messageEventHandler.messageEvent
        }
    }


}
