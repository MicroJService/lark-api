package org.microjservice.lark.api.v3.models

import org.microjservice.lark.api.models.Page

/**
 * DepartmentPage
 *
 * @author Coder Yellow
 * @since 0.1.5
 */
class DepartmentPage(
    hasMore: Boolean,
    pageToken: String?,
    val items: List<Department>,
) : Page(hasMore, pageToken)
