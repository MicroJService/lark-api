package org.microjservice.lark.org.microjservice.lark.api

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldNotBe
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import org.microjservice.lark.api.CardMessageApi
import org.microjservice.lark.api.ChatApi
import org.microjservice.lark.api.models.Card
import org.microjservice.lark.api.models.CardMessage

@MicronautTest
class CardMessageApiTest(
    private val cardMessageApi: CardMessageApi,
    private val chatApi: ChatApi
) : BehaviorSpec({

    given("CardMessageApi") {
        `when`("send CardMessage") {
            val cardMessage = CardMessage(
                chatApi.list().data.groups.first().chatId,
                "interactive",
                null,
                null,
                Card(
                    Card.Config(null, null),
                    Card.Header(
                        Card.Text(
                            Card.Text.Tag.PLAIN_TEXT,
                            "Title",
                            1,
                            Card.Text.I18N("标题", "Title", "标题"),
                        ),
                        Card.Header.Template.BLUE
                    ),
                    listOf(
                        Card.Module.DivModule(
                            Card.Text(
                                Card.Text.Tag.PLAIN_TEXT,
                                "text-lark_md",
                                1,
                                null,
                            ),
                            listOf(
                                Card.Field(
                                    false,
                                    Card.Text(
                                        Card.Text.Tag.LARK_MD,
                                        "<a>https://open.feishu.cn</a>",
                                        null,
                                        null,
                                    )
                                ),
                                Card.Field(
                                    false,
                                    Card.Text(
                                        Card.Text.Tag.LARK_MD,
                                        "ready\\nnew line",
                                        null,
                                        null,
                                    )
                                )
                            ),
                            null
                        )
                    )
                )
            )
            val response = cardMessageApi.send(cardMessage)
            then("the result is not null") {
                response shouldNotBe null
            }
        }

    }

})
