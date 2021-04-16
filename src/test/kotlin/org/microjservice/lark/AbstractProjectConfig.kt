package org.microjservice.lark
import io.kotest.core.config.AbstractProjectConfig
import io.micronaut.test.extensions.kotest.MicronautKotestExtension

/**
 * AbstractProjectConfig
 *
 * @author Coder Yellow
 * @since 0.1.0
 */

object ProjectConfig : AbstractProjectConfig() {
    override fun listeners() = listOf(MicronautKotestExtension)
    override fun extensions() = listOf(MicronautKotestExtension)
}