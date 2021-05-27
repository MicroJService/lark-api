package org.microjservice.lark.api.v1

import io.micronaut.test.extensions.spock.annotation.MicronautTest
import org.microjservice.lark.api.models.BaseResponse
import org.microjservice.lark.api.v1.models.BatchUser
import spock.lang.Specification

import javax.inject.Inject

/**
 * ${description}*
 * @author Coder Yellow
 * @since ${version}
 */

@MicronautTest
class UserApiSpec extends Specification {

    @Inject
    UserApi userApi;

    def "should return user id"() {
        given:
        def userIds = userApi.batchGetId(["coderyellow@hotmail.com"], null)
                .map(UserApi::getOpenIds)
                .blockingGet()

        expect:
        userIds.isPresent()
    }
}
