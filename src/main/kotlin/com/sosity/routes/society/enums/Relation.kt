package com.sosity.routes.society.enums

import kotlinx.serialization.Serializable

@Serializable
enum class Relation {
    HUSBAND,
    WIFE,
    FATHER,
    MOTHER,
    CHILD,
    OTHER
}