package com.sosity.routes.society.tables

import com.sosity.routes.society.enums.VehicleType
import kotlinx.serialization.Serializable

@Serializable
data class Vehicle(val name: String, val number: String, val type: VehicleType)