package org.microjservice.lark.api.im.v1

import com.github.javafaker.Faker
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import io.reactivex.Single
import jakarta.inject.Inject
import org.microjservice.lark.api.im.v1.modles.Chat
import org.microjservice.lark.api.models.BaseResponse
import org.microjservice.lark.api.models.UserIdType
import org.microjservice.lark.api.v1.UserApi
import org.microjservice.lark.api.v1.models.BatchUser
import org.microjservice.lark.api.v1.models.BatchUser.UserId
import spock.lang.Specification

import java.util.function.Function
import java.util.stream.Collectors

import static org.microjservice.lark.api.im.v1.modles.Chat.ApprovalPermission.NO_APPROVAL_REQUIRED
import static org.microjservice.lark.api.im.v1.modles.Chat.ChatMode.GROUP
import static org.microjservice.lark.api.im.v1.modles.Chat.ChatType.PUBLIC
import static org.microjservice.lark.api.im.v1.modles.Chat.MemberPermission.ALL_MEMBERS


/**
 * ${description}*
 * @author Coder Yellow
 * @since ${version}
 */
@MicronautTest
class ChatApiSpec extends Specification {
    @Inject
    ChatApi chatApi

    @Inject
    UserApi userApi

    def "should create chat"() {
        when:
        def response = chatApi.create(new Chat(
                "",
                "TEST",
                "Test chat created by lark-api",
                null,
                GROUP,
                PUBLIC,
                ALL_MEMBERS,
                ALL_MEMBERS,
                NO_APPROVAL_REQUIRED
        ))
                .blockingGet()
        println response.data
        then:
        response.getMsg() == "ok"
    }

    def "should add members"() {
        given:
        def userOpenIds = userApi
                .batchGetId(["coderyellow@hotmail.com", "hgj1995@163.com"], null)
                .blockingGet()
                .data.emailUsers.values()
                .stream().map({ it.getOpenId() } as Function<UserId, String>)
                .collect(Collectors.toList())

        def chatId = chatApi.create(new Chat(
                null,
                "TEST-"+ new Faker().internet().password(4,8),
                "Test chat created for testing adding members",
                null,
                GROUP,
                PUBLIC,
                ALL_MEMBERS,
                ALL_MEMBERS,
                NO_APPROVAL_REQUIRED
        ))
                .blockingGet().data.chatId
        println chatId
        expect:
        null != userOpenIds && userOpenIds.size() > 0
        null != chatId

        when:
        def chat = chatApi
                .addMembers(
                        chatId,
                        UserIdType.OPEN_ID,
                        userOpenIds
                )
                .blockingGet()
                .data
        then:
        null != chat && 0 == chat.invalidIdList.size()
        notThrown(Exception)

    }
}
