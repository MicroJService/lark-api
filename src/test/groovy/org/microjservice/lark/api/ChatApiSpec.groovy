package org.microjservice.lark.api

import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.platform.commons.util.StringUtils

import spock.lang.Specification


/**
 * ${description}*
 * @author Coder Yellow
 * @since ${version}
 */
@MicronautTest
class ChatApiSpec extends Specification {

    @Inject
    ChatApi chatApi

    def "should return chat list"() {
        given:
        def chatList = chatApi.list()
        println chatList.getData()
        expect:
        StringUtils.isNotBlank(
                chatList
                        .getData()
                        .getGroups()
                        .stream()
                        .findAny().
                        get().chatId
        )
    }
}
