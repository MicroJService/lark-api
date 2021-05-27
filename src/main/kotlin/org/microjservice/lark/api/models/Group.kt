package org.microjservice.lark.api.models

import io.micronaut.core.annotation.Introspected

/**
 * Group
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
@Introspected
data class Group (
    val avatar: String,
    val chatId: String,
    val description: String,
    val name: String,
    var ownerOpenId: String?,
    var ownerUserId: String?,
)
