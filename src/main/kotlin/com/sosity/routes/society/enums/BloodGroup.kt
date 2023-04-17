package com.sosity.routes.society.enums

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class BloodGroup(private val value: String) {
    @SerialName("A+")
    APlus("A+"),

    @SerialName("A-")
    AMinus("A-"),

    @SerialName("AB+")
    ABPlus("AB+"),

    @SerialName("AB-")
    ABMinus("AB-"),

    @SerialName("B+")
    BPlus("B+"),

    @SerialName("B-")
    BMinus("B-"),

    @SerialName("O+")
    OPlus("O+"),

    @SerialName("O-")
    OMinus("O-"),

    @SerialName("Unknown")
    Unknown("Unknown");

    override fun toString(): String {
        return value
    }
}