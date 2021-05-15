/*
 * Copyright (c) 2019 Bytedance Inc.  All rights reserved.
 * Use of this source code is governed by a MIT style
 * license that can be found in the LICENSE file.
 * Copied from https://github.com/larksuite/appframework-java/blob/48b0f6a3dfc047fe8f02d43f4310c1ae41622e96/appframework-sdk/src/main/java/com/larksuite/appframework/sdk/core/auth/NotifyDataDecrypter.java. Modified by Coder Yellow.
 */
package org.microjservice.lark.core.event.crypto

import io.micronaut.context.annotation.Value
import jakarta.inject.Singleton
import java.nio.charset.StandardCharsets
import java.security.InvalidAlgorithmParameterException
import java.security.InvalidKeyException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

@Singleton
class NotifyDataDecrypter(
    @Value("\${lark.encrypt-key}")
    private val encryptKey: String
) {
    private val key: ByteArray

    @Throws(
        InvalidAlgorithmParameterException::class,
        InvalidKeyException::class,
        BadPaddingException::class,
        IllegalBlockSizeException::class,
        NoSuchPaddingException::class,
        NoSuchAlgorithmException::class
    )
    fun decrypt(base64: String?): String {
        val decode = Base64.getDecoder().decode(base64)
        val cipher = Cipher.getInstance("AES/CBC/NOPADDING")
        val iv = ByteArray(16)
        System.arraycopy(decode, 0, iv, 0, 16)
        val data = ByteArray(decode.size - 16)
        System.arraycopy(decode, 16, data, 0, data.size)
        cipher.init(Cipher.DECRYPT_MODE, SecretKeySpec(key, "AES"), IvParameterSpec(iv))
        var decryptedData = cipher.doFinal(data)
        if (decryptedData.isNotEmpty()) {
            var p = decryptedData.size - 1
            while (p >= 0 && decryptedData[p] < 16) {
                p--
            }
            if (p != decryptedData.size - 1) {
                val rr = ByteArray(p + 1)
                System.arraycopy(decryptedData, 0, rr, 0, p + 1)
                decryptedData = rr
            }
        }
        return String(decryptedData, StandardCharsets.UTF_8)
    }

    init {
        var digest: MessageDigest? = null
        try {
            digest = MessageDigest.getInstance("SHA-256")
        } catch (e: NoSuchAlgorithmException) {
            // won't happen
        }
        key = digest!!.digest(encryptKey.toByteArray(StandardCharsets.UTF_8))
    }
}