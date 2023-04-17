package com.sosity.models

import io.ktor.http.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
sealed class Errors(val error: String, val code: Int)

@Serializable
data class BodyError(@Transient val msg: String = "") : Errors(msg, HttpStatusCode.BadRequest.value)

@Serializable
data class ContentNotFound(@Transient val msg: String = "") : Errors(msg, HttpStatusCode.NoContent.value)

@Serializable
data class DuplicateFound(
    @Transient val msg: String = "",
    val duplicate: Boolean = true
) : Errors(msg, HttpStatusCode.Conflict.value)

@Serializable
data class GenericException(@Transient val msg: String = "") : Errors(msg, HttpStatusCode.InternalServerError.value)