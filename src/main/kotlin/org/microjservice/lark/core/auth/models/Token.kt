package org.microjservice.lark.core.auth.models

data class Token(
    val accessToken: String,
    val expireTime: Long,
) {
    private val expiredTime = System.currentTimeMillis() + expireTime * 1000

    fun isExpired(): Boolean {
        return System.currentTimeMillis() > expiredTime
    }
}
