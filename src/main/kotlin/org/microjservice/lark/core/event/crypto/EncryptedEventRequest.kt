package org.microjservice.lark.core.event.crypto

import io.micronaut.core.annotation.Introspected

/**
 * Encrypted Request
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
@Introspected
data class EncryptedEventRequest(
    val encrypt: String
)