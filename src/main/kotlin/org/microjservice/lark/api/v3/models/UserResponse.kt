package org.microjservice.lark.api.v3.models

import com.fasterxml.jackson.annotation.JsonProperty
import java.net.URL

/**
 * User Response
 *
 * @author Coder Yellow
 * @since 0.1.4
 */
data class UserResponse(
    val user: User,
) {
    class User(
        val avatar: Avatar,
        val city: String,
        val country: String,
        val departmentIds: List<String>,
        val description: String,
        val email: String,
        val employeeNo: String,
        val employeeType: Int,
        val enName: String,
        val gender: String,
        val isTenantManager: String,
        val joinTime: Int,
        val mobile: String?,
        val name: String,
        val openId: String,
        val orders: List<Order>,
        val status: Status,
        val unionId: String,
        val userId: String,
        val workStation: String,
    ) {
        class Avatar(
            @JsonProperty("avatar_240")
            val avatar240: URL,
            @JsonProperty("avatar_640")
            val avatar640: URL,
            @JsonProperty("avatar_72")
            val avatar72: URL,
            val avatarOrigin: URL,
        )

        class Order(
            val departmentId: String,
            val departmentOrder: Int,
            val userOrder: Int,
        )

        class Status(
            val isActivated: Boolean,
            val isFrozen: Boolean,
            val isResigned: Boolean,
        )
    }
}
