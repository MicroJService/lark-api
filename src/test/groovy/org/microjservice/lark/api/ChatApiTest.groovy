package org.microjservice.lark.api

import io.micronaut.test.extensions.spock.annotation.MicronautTest
import org.junit.platform.commons.util.StringUtils
import org.microjservice.lark.core.auth.AuthorizationApi
import org.spockframework.util.CollectionUtil
import spock.lang.Specification

import javax.inject.Inject

/**
 * ${description}*
 * @author Coder Yellow
 * @since ${version}
 */
@MicronautTest
class ChatApiTest extends Specification {

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
