package com.sosity.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.litote.kmongo.Id
import org.litote.kmongo.newId
import java.time.LocalDateTime


@Serializable
abstract class BaseDbModel<T>(
    @Contextual val updateDateTime: LocalDateTime = LocalDateTime.now(),
    @Contextual val createDateTime: LocalDateTime = LocalDateTime.now(),
    @Contextual @SerialName("_id") val id: Id<T> = newId(),
)