package com.sosity.routes.society.tables

import com.sosity.models.BaseDbModel
import com.sosity.routes.society.enums.BloodGroup
import kotlinx.serialization.Serializable


@Serializable
data class Person(
    val name: String,
    val photo: String? = null,
    val education: String? = null,
    val houseNumber: String,
    val residentialAddress: String? = null,
    val permanentAddress: String? = null,
    val dateOfBirth: String? = null,
    val dateOfMarriageAnniversary: String? = null,
    val profession: String? = null,
    val designation: String? = null,
    val officeAddress: String? = null,
    val mobile: String? = null,
    val officePhone: String? = null,
    val email: String? = null,
    val bloodGroup: BloodGroup = BloodGroup.Unknown,
    val familyMembers: List<FamilyMember> = listOf(),
    val vehicles: List<Vehicle> = listOf(),
    val fieldOfInterest: String? = null,
    val activeCorporation: Boolean = true,
    val association: String? = null
) : BaseDbModel<Person>()

