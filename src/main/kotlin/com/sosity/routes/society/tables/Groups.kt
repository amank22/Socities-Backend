package com.sosity.routes.society.tables

import com.sosity.routes.society.enums.GroupType
import kotlinx.serialization.Serializable

@Serializable
data class Group(val name: String, val groupType: GroupType, val groupMembers: List<GroupMember>)