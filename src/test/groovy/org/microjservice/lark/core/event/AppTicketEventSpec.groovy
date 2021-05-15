package org.microjservice.lark.core.event

import io.micronaut.context.annotation.Value
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.platform.commons.util.StringUtils
import org.microjservice.lark.LarkClient
import org.microjservice.lark.core.auth.models.Credential
import spock.lang.Shared
import spock.lang.Specification
import spock.util.concurrent.PollingConditions

/**
 * ${description}*
 * @author Coder Yellow
 * @since ${version}
 */
@MicronautTest
class AppTicketEventSpec extends Specification {
    @Value("\${lark.app-id}")
    String appId
    @Value("\${lark.app-secret}")
    String appSecret
    @Value("\${lark.endpoint}")
    String endpoint
    @Inject
    Credential credential
    @Inject
    TicketManager ticketManager

    @Shared
    LarkClient larkClient


    def setupSpec() {
        larkClient  = new LarkClient.Builder()
                .withCredential(
                        new Credential("cli_a0caf2b8cf38d009", "RGEjA4CYzteyEzobOiBfkcjNMIb4BxzU", Credential.CredentialType.INTERNAL_APP)
                )
                .withEndpoint("https://open.larksuite.com/open-apis")
                .withEventHandler(baseEvent -> println(baseEvent))
                .build()
    }

    def "should receive app ticket event"() {
        PollingConditions conditions = new PollingConditions(delay: 1, timeout: 512)

        larkClient.authorizationApi.resendAppTicket(credential)

        expect:
        conditions.eventually {
            StringUtils.isNotBlank(ticketManager.getTicket(appId))
        }

    }
}
