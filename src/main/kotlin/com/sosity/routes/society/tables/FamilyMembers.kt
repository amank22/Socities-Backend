package com.sosity.routes.society.tables

import com.sosity.routes.society.enums.BloodGroup
import com.sosity.routes.society.enums.Relation
import kotlinx.serialization.Serializable

@Serializable
data class FamilyMember(
    val name: String,
    val relationship: Relation,
    val dateOfBirth: String? = null,
    val education: String? = null,
    val bloodGroup: BloodGroup = BloodGroup.Unknown,
    val remarks: String? = ""
)
