package org.microjservice.lark.core.event

import jakarta.inject.Singleton

/**
 * App Ticket Manager
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
@Singleton
class TicketManager {
    private val appTickets = mutableMapOf<String, String>()

    fun setTicket(appId: String, ticket: String) {
        appTickets[appId] = ticket
    }

    fun getTicket(appId: String): String? {
        return appTickets[appId]
    }
}