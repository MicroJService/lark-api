package org.microjservice.lark.core.event.crypto

import io.micronaut.core.bind.annotation.Bindable

/**
 * Larksuite Encrypted Request
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Bindable
annotation class EncryptedRequest
