package org.microjservice.lark.api.im.v1

import io.micronaut.http.HttpRequest
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.multipart.MultipartBody
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import jakarta.inject.Singleton
import java.io.File
import java.io.InputStream
import java.net.URI

/**
 * ImageUploadApi
 *
 * @author Coder Yellow
 * @since 0.1.5
 */
@Singleton
class ImageUploadApi(
    private val imageApi: ImageApi,
    private val rxHttpClient: RxHttpClient,
) {
    companion object {
        const val IMAGE_TYPE_FIELD_NAME: String = "image_type"
        const val IMAGE_FIELD_NAME: String = "image"
    }

    enum class ImageType {
        MESSAGE, AVATAR;

        override fun toString(): String {
            return super.toString().toLowerCase()
        }
    }

    fun uploadImage(imageType: ImageType, file: File): Single<String> {

        return imageApi.uploadImage(
            MultipartBody.builder()
                .addPart(IMAGE_TYPE_FIELD_NAME, imageType.toString())
                .addPart(IMAGE_FIELD_NAME, file)
                .build()
        )
            .map { it.data.imageKey }
    }

    fun uploadImage(imageType: ImageType, imageName: String, data: ByteArray): Single<String> {

        return imageApi.uploadImage(
            MultipartBody.builder()
                .addPart(IMAGE_TYPE_FIELD_NAME, imageType.toString())
                .addPart(IMAGE_FIELD_NAME, imageName, data)
                .build()
        )
            .map { it.data.imageKey }
    }

    fun uploadImage(
        imageType: ImageType,
        imageName: String,
        data: InputStream,
        contentLength: Long
    ): Single<String> {

        return imageApi.uploadImage(
            MultipartBody.builder()
                .addPart(IMAGE_TYPE_FIELD_NAME, imageType.toString())
                .addPart(IMAGE_FIELD_NAME, imageName, data, contentLength)
                .build()
        )
            .map { it.data.imageKey }
    }

    fun uploadImage(imageType: ImageType, uri: URI): Single<String> {
        return rxHttpClient.retrieve(
            HttpRequest.GET<Any>(uri),
            ByteArray::class.java
        )
            .subscribeOn(Schedulers.io())
            .publish()
            .autoConnect()
            .firstOrError()
            .flatMap {
                uploadImage(imageType, File(uri.path).name, it)
            }
    }
}