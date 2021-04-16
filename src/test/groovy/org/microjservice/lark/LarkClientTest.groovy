package org.microjservice.lark

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import io.micronaut.context.annotation.Value
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import org.junit.platform.commons.util.StringUtils
import spock.lang.Specification

/**
 * Unit test for {@link LarkClient}
 * @author Coder Yellow
 * @since 0.1.0
 */
@MicronautTest
class LarkClientTest extends Specification {
    @Value("\${lark.app-id}")
    String appId
    @Value("\${lark.app-secret}")
    String appSecret
    @Value("\${lark.endpoint}")
    String endpoint

    def "test"() {
        expect:
        StringUtils.isNotBlank(appId) && StringUtils.isNotBlank(endpoint)
    }

    def "jackson test"() {

        def objectMapper = new ObjectMapper()
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
        expect:
        StringUtils.isNotBlank(appId) && StringUtils.isNotBlank(endpoint)
    }
}
