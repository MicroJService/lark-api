package org.microjservice.lark.api.im.v1

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Part
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Produces
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.multipart.MultipartBody
import io.micronaut.http.multipart.CompletedFileUpload
import io.micronaut.http.multipart.PartData
import io.reactivex.Single
import org.microjservice.lark.api.im.v1.modles.ImageResponse
import org.microjservice.lark.api.models.BaseResponse
import java.io.File

/**
 * Image Api
 *
 * @author Coder Yellow
 * @since 0.1.5
 */
@Client("\${lark.endpoint}/im/v1/images")
interface ImageApi {

    @Post
    @Produces(MediaType.MULTIPART_FORM_DATA)
    fun uploadImage(@Body body: MultipartBody): Single<BaseResponse<ImageResponse>>

}