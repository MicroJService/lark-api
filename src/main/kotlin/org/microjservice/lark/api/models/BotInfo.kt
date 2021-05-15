package org.microjservice.lark.api.models

import io.micronaut.core.annotation.Introspected
import java.net.URL

/**
 * BotInfo
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
@Introspected
data class BotInfo(
    val activateStatus: Int,
    val appName: String,
    val avatarUrl: URL,
    val ipWhiteList: List<String>,
    val openId: String,
)


