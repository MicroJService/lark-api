package org.microjservice.lark.core.event.model

/**
 * Url Verification Event
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
data class UrlVerificationEvent(
    val challenge: String,
    val token: String,
    val type: String,
)