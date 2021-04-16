package org.microjservice.lark.api.models

import io.micronaut.core.annotation.Introspected

@Introspected
open class Page(
    val hasMore: Boolean,
    val pageToken: String,
)
