package org.microjservice.lark.api.models

import io.micronaut.core.annotation.Introspected

/**
 * Group Page
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
@Introspected
data class GroupPage(
    val hasMore: Boolean,
    val pageToken: String,
    val groups: List<Group>,
)