package org.microjservice.lark.api.v1.models

/**
 * Batch User
 *
 * @author Coder Yellow
 * @since 0.1.3
 */
class BatchUser(
    val emailUsers: Map<String, UserId>?,
    val emailsNotExist: List<String>?,
    val mobileUsers: Map<String, UserId>?,
    val mobilesNotExist: List<String>?,
) {
    data class UserId(
        val openId: String,
        val userId: String,
    )
}
