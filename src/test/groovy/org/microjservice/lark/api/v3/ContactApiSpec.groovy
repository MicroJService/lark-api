package org.microjservice.lark.api.v3

import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import org.microjservice.lark.api.models.UserIdType
import spock.lang.Shared
import spock.lang.Specification


/**
 * ${description}*
 * @author Coder Yellow
 * @since ${version}
 */
@MicronautTest
class ContactApiSpec extends Specification {

    @Inject
    ContactApi contactApi

    @Shared
    String userId = System.getenv("USER_ID")

    def "should return user"() {
        given:
        def user = contactApi.user(userId, UserIdType.USER_ID).data.user
        println user
        expect:
        null != user
    }
}
