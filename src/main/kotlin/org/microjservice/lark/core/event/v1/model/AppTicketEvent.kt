package org.microjservice.lark.core.event.v1.model

/**
 * app_ticket event
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
data class AppTicketEvent(
    val ts: String,
    val uuid: String,
    val token: String,
    val type: String,
    val event: AppTicket
) {
    data class AppTicket(
        val appId: String,
        val appTicket: String,
        val type: String,
    )
}