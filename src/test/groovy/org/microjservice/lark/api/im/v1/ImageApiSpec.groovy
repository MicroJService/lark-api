package org.microjservice.lark.api.im.v1

import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification

/**
 * ${description}*
 * @author Coder Yellow
 * @since ${version}
 */
@MicronautTest
class ImageApiSpec extends Specification {

    @Inject
    ImageUploadApi imageUploadApi

    def "should return image key"() {
        given:
        def imageKey
        def file = new File("/home/coderyellow/Pictures/512px-Pornhub-logo.svg.png")
        def uri = new URI("https://gitlab.microjservice.org/uploads/-/system/group/avatar/17/im_scrum_logo.png")

        when:
        imageKey = imageUploadApi.uploadImage(ImageUploadApi.ImageType.MESSAGE, file).blockingGet()
        println imageKey
        then:
        null != imageKey

        when:
        imageKey = imageUploadApi.uploadImage(ImageUploadApi.ImageType.MESSAGE, file.name, file.bytes).blockingGet()
        println imageKey
        then:
        null != imageKey

        when:
        imageKey = imageUploadApi.uploadImage(ImageUploadApi.ImageType.MESSAGE, file.name, new FileInputStream(file), file.length()).blockingGet()
        println imageKey
        then:
        null != imageKey



    }

    def "should upload by uri"() {
        given:
        def imageKey
        def uri = new URI("http://localhost:9199/uploads/group/avatar/17/im_scrum_logo.png?response-content-disposition=inline%3B%20filename%3D%22im_scrum_logo.png%22%3B%20filename%2A%3DUTF-8%27%27im_scrum_logo.png&response-content-type=image%2Fpng&X-Amz-Expires=600&X-Amz-Date=20210522T124537Z&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=minio%2F20210522%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-SignedHeaders=host&X-Amz-Signature=a88998c3008f018072fa0350639f0a393f88ffc3421f2d538169acc73ab36e41")

        when:
        imageKey = imageUploadApi.uploadImage(ImageUploadApi.ImageType.MESSAGE, uri).blockingGet()
        println "url" + imageKey
        then:
        null != imageKey
    }
}
