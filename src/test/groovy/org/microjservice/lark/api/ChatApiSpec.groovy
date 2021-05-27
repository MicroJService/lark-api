package org.microjservice.lark.api

import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.platform.commons.util.StringUtils
import org.microjservice.lark.api.models.Group
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

        def groups = chatList
                .map(baseResponse -> baseResponse.getData().getGroups())
                .blockingGet()
        println groups
        expect:
        StringUtils.isNotBlank(
                groups
                        .stream()
                        .findAny().
                        get().chatId
        )
    }
}
