package org.microjservice.lark.api.models

/**
 * Bot Response
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
class BotResponse(
    code: Int,
    msg: String,
    val bot: BotInfo,
) : Response(code, msg)