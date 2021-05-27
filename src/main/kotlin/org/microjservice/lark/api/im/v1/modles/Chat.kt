package org.microjservice.lark.api.im.v1.modles

import com.fasterxml.jackson.annotation.JsonProperty
import org.microjservice.lark.api.models.I18NNames

class Chat(
    val chatId: String?,
    val avatar: String?,
    val name: String?,
    val description: String?,
    val i18NNames: I18NNames?,
    val addMemberPermission: MemberPermission?,
    val shareCardPermission: SharePermission?,
    val atAllPermission: MemberPermission?,
    val editPermission: MemberPermission?,
    val chatMode: ChatMode?,
    val chatType: ChatType?,
    val chatTag: String?,
    val joinMessageVisibility: MemberPermission?,
    val leaveMessageVisibility: MemberPermission?,
    val membershipApproval: ApprovalPermission?,
    val moderationPermission: MemberPermission?,
) {


    enum class MemberPermission {
        @JsonProperty("all_members")
        ALL_MEMBERS,

        @JsonProperty("only_owner")
        ONLY_OWNER,

        @JsonProperty("not_anyone")
        NOT_ANYONE,

        @JsonProperty("moderator_list")
        MODERATOR_LIST
    }

    enum class SharePermission {
        @JsonProperty("allowed")
        ALLOWED,

        @JsonProperty("not_allowed")
        NOT_ALLOWED
    }

    enum class ChatMode {
        @JsonProperty("group")
        GROUP
    }

    enum class ChatType {
        @JsonProperty("private")
        PRIVATE,

        @JsonProperty("public")
        PUBLIC
    }

    enum class ApprovalPermission {
        @JsonProperty("no_approval_required")
        NO_APPROVAL_REQUIRED,

        @JsonProperty("approval_required")
        APPROVAL_REQUIRED
    }

    constructor(
        avatar: String?,
        name: String?,
        description: String?,
        i18NNames: I18NNames?,
        chatMode: ChatMode?,
        chatType: ChatType?,
        joinMessageVisibility: MemberPermission?,
        leaveMessageVisibility: MemberPermission?,
        membershipApproval: ApprovalPermission?,
    ) : this(
        null,
        avatar,
        name,
        description,
        i18NNames,
        null,
        null,
        null,
        null, chatMode,
        chatType,
        null,
        joinMessageVisibility,
        leaveMessageVisibility,
        membershipApproval,
        null,
    )
}
