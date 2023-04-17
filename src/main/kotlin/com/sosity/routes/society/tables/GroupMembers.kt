package com.sosity.routes.society.tables

import kotlinx.serialization.Serializable

@Serializable
data class GroupMember(val person: Person, val role: String)