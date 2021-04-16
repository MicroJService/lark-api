package org.microjservice.lark.core.auth.models

data class Token(
    val accessToken: String,
    val expireTime: Long,
) {
    fun isExpired(): Boolean {
        return System.currentTimeMillis() > expireTime
    }
}
