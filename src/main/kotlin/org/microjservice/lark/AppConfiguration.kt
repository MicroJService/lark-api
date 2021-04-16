package org.microjservice.lark

import io.micronaut.context.annotation.Value

/**
 * Configuration for Lark suite.
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
 data class AppConfiguration(
    @Value("") var endpoint: String
)