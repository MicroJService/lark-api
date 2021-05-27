package org.microjservice.lark.api.v3.models

import io.micronaut.core.annotation.Introspected
import io.micronaut.http.annotation.QueryValue
import org.microjservice.lark.api.models.UserIdType

/**
 * DepartmentQuery
 *
 * @author Coder Yellow
 * @since 0.1.5
 */
@Introspected
data class DepartmentQuery(
    @field:QueryValue("user_id_type")
    val userIdType: UserIdType?,
    @field:QueryValue("department_id_type")
    val departmentIdType: DepartmentIdType?,
    @field:QueryValue("parent_department_id")
    val parentDepartmentId: String?,
    @field:QueryValue("fetch_child")
    val fetchChild: Boolean?,
) {
    enum class DepartmentIdType {
        DEPARTMENT_ID, OPEN_DEPARTMENT_ID;

        override fun toString(): String {
            return super.toString().toLowerCase()
        }
    }
}
