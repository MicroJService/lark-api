package org.microjservice.lark

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.should
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import org.microjservice.lark.api.models.Group

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
}) {
//    constructor():this(ObjectMapper())
}
