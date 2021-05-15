package org.microjservice.lark.core.event.v1.model

/**
 * Base Event Request
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
class BaseEventRequest(
    val appId: String,
    val tenantKey: String,
    val type: Type,
) {
    enum class Type(val typeName: String) {
        URL_VERIFICATION("url_verification")
    }
}