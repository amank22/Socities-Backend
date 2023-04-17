package com.sosity.models

import kotlinx.serialization.Serializable

@Serializable
sealed class Response(
    val success: Boolean
) {
    @Serializable
    data class Success<T>(
        val data: T,
    ) : Response(true)

    @Serializable
    class Failure(
        val error: Errors
    ) : Response(false)
}