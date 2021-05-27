package org.microjservice.lark.api

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldNotBe
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import org.microjservice.lark.LarkClient
import org.microjservice.lark.api.models.MessageRequest
import org.microjservice.lark.api.models.MessageRequest.*
import org.microjservice.lark.api.models.message
import java.util.*

/**
 *
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
@MicronautTest
class MessageSpec(
    private val larkClient: LarkClient
) : StringSpec({
    val message = message(language = Locale.US) {
        title { +"Title" }
        content {
            line {
                text { +"First line" }
                a(href = "https://github.com/MicroJService/lark-api") { +"Hyperlinks" }
                at(userid = "ou_1avnmsbv3k45jnk34j5") { +"tom" }
            }

            line {
                img(imagekey = "img_v2_80f5e116-af07-42a6-8df7-d7f07b14f1fg", width = 300, height = 300)
            }

            line {
                text { +"Second line:" }
                text { +"text testing" }
            }

            line {
                img(imagekey = "img_v2_80f5e116-af07-42a6-8df7-d7f07b14f1fg", width = 300, height = 300)
            }
        }
    }

    "should return message" {


        println(message)

        val richText = message.toRichTextContent()
        println(richText)

        richText shouldNotBe null
    }

    "should send message"{
        larkClient.messageApi.send(
            ReceiveIdType.CHAT_ID,
            MessageRequest(
                "oc_760ffdaf006eb1b422af7bf64f8df2ec",
                I18nContent( null,message.toRichTextContent()),
                MessageType.POST
            )
        )

        shouldNotThrow<Exception> {  }
    }
}
)