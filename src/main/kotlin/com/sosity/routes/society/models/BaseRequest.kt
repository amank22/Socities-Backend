package com.sosity.routes.society.models

import kotlinx.serialization.Serializable

@Serializable
data class BaseRequest<T>(val data : T)
