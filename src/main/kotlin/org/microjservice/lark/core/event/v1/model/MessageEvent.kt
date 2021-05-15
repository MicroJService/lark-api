package org.microjservice.lark.core.event.v1.model

import org.microjservice.lark.core.event.v1.model.BaseEvent

/**
 * Message Event
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
data class MessageEvent(
    val appId: String,
    val chatType: String,
    val employeeId: String,
    val isMention: String,
    val larkVersion: String,
    val messageId: String,
    val msgType: String,
    val openChatId: String,
    val openId: String,
    val openMessageId: String,
    val parentId: String,
    val rootId: String,
    val tenantKey: String,
    val text: String,
    val textWithoutAtBot: String,
    val unionId: String,
    val userAgent: String,
    val userOpenId: String,
) : BaseEvent(Type.MESSAGE)