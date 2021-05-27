package org.microjservice.lark.api.models

/**
 *
 *
 * @author Coder Yellow
 * @since 0.1.5
 */
enum class UserIdType {
    OPEN_ID,
    UNION_ID,
    USER_ID;

    override fun toString(): String {
        return super.toString().toLowerCase()
    }
}