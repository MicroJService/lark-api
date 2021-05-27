package org.microjservice.lark.api.v3.models

import org.microjservice.lark.api.models.I18NNames

/**
 * Department
 *
 * @author Coder Yellow
 * @since 0.1.5
 */
data class Department(
    val name: String,
    val i18nName: I18NNames?,
    val parentDepartmentId: String?,
    val departmentId: String,
    val openDepartmentId: String,
    val leaderUserId: String?,
    val chatId: String?,
    val order: String?,
    val unitIds: List<String>?,
    val memberCount: Int,
    val status: Status?,
) {
    class Status(val isDeleted: Boolean)
}
