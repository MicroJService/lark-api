package org.microjservice.lark.api.v3

import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import org.codehaus.groovy.runtime.DefaultGroovyMethods
import org.junit.platform.commons.util.CollectionUtils
import org.microjservice.lark.api.GitHubApi
import org.microjservice.lark.api.models.BaseResponse
import org.microjservice.lark.api.models.PageQuery
import org.microjservice.lark.api.models.UserIdType
import org.microjservice.lark.api.v3.models.DepartmentPage
import org.microjservice.lark.api.v3.models.DepartmentQuery
import spock.lang.Specification

/**
 * ${description}*
 * @author Coder Yellow
 * @since ${version}
 */
@MicronautTest
class DepartmentApiSpec extends Specification {
    @Inject
    DepartmentApi departmentApi

    @Inject
    GitHubApi gitHubApi

    def "should return departments"() {
        given:
        def departments = departmentApi
                .get(
                        new DepartmentQuery(UserIdType.OPEN_ID, DepartmentQuery.DepartmentIdType.OPEN_DEPARTMENT_ID, "0", false),
                        new PageQuery(20, null))
                .blockingGet().data.items
        departments.forEach(DefaultGroovyMethods::println)
        expect:
        null != departments && departments.size() > 0

        when:
        departments = departmentApi
                .get(null, null)
                .blockingGet().data.items
        then:
        null != departments && departments.size() > 0

    }
}
