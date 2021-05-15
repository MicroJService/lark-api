package org.microjservice.lark.api.models

import io.micronaut.core.annotation.Introspected

/**
 * Group Page
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
@Introspected
class GroupPage(
    hasMore: Boolean,
    pageToken: String,
    val groups: List<Group>,
) : Page(hasMore, pageToken)