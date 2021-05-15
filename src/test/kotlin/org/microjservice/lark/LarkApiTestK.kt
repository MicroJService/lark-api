package org.microjservice.lark

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.should
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import org.microjservice.lark.api.models.Group
import org.microjservice.lark.core.event.v2.model.EventRequest

/**
 * ${description}*
 * @author Coder Yellow
 * @since ${version}
 */

@MicronautTest
class LarkApiTestK(objectMapper: ObjectMapper) : BehaviorSpec({
    given("object mapper") {

        `when`("the property-naming-strategy is SNAKE_CASE") {
            val jsonString = """
            {
              "avatar": "https://p21-lark-file-va.ibyteimg.com/img/lark-avatar-va/default-avatar_505bdec1-3019-492d-8a4e-0b0af1e238c8~100x100.jpg",
              "chat_id": "oc_5b64058f0c9b69a9250d1700c0834a87",
              "description": "",
              "name": "MicroJService",
              "owner_open_id": "ou_d8eeee75271296e647c1ce0beff4dc21",
              "owner_user_id": "427c3367"
            }
            """
            println("Hello ")
            val group = objectMapper.readValue(jsonString, Group::class.java)
            then("json deserialized") {
                group should ({ group -> group != null })
            }
        }
    }

    given("object mapper and json string") {

        `when`("using custom deserializer") {
            val jsonString = """
            {"schema":"2.0","header":{"event_id":"bbc0270fbf387e36b6545119405d0ee7","token":"2VcDo20DuC2GHsN88IN9YdMrqYM2prld","create_time":"1620531589000","event_type":"im.message.receive_v1","tenant_key":"135f63f91b0f1740","app_id":"cli_a0b12d49df789013"},"event":{"message":{"chat_id":"oc_87d7e55fa83069ec9aab69dfb5dec374","chat_type":"group","content":"{\"text\":\"@_user_1 \"}","create_time":"1620531588352","mentions":[{"id":{"open_id":"ou_513116e20e172e7da9084e25ff9eb200","union_id":"on_2c2ffdaf177f9af6b8ff0c0b5cded4ea","user_id":""},"key":"@_user_1","name":"Test","tenant_key":"135f63f91b0f1740"}],"message_id":"om_b651c203420c18fa527e349991eff1f7","message_type":"text"},"sender":{"sender_id":{"open_id":"ou_eda08fd6c2374597cdf3f9154526e456","union_id":"on_646a32e9ff0ac3fd703435c6bbb0d29d","user_id":"9dg5g21c"},"sender_type":"user","tenant_key":"135f63f91b0f1740"}}}
            """
            objectMapper.registerKotlinModule()
            val messageEvent = objectMapper.readValue(jsonString, EventRequest::class.java)
            println(messageEvent)
            then("json deserialized") {
                messageEvent should ({ group -> group != null })
            }
        }
    }

})
