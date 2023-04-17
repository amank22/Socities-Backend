package com.sosity.helpers

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import org.litote.kmongo.id.serialization.IdKotlinXSerializationModule
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalSerializationApi::class)
val jsonInstance = Json {
    serializersModule = SerializersModule {
        include(IdKotlinXSerializationModule)
        contextual(LocalDate::class, LocalDateSerializer)
        contextual(LocalDateTime::class, LocalDateTimeSerializer)
    }
    ignoreUnknownKeys = true
    isLenient = true
    encodeDefaults = false
    explicitNulls = false
}

object LocalDateSerializer : KSerializer<LocalDate> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("LocalDate", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: LocalDate) {
        val result = value.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        encoder.encodeString(result)
    }

    override fun deserialize(decoder: Decoder): LocalDate {
        return LocalDate.parse(decoder.decodeString())
    }
}

object LocalDateTimeSerializer : KSerializer<LocalDateTime> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("LocalDateTime", PrimitiveKind.LONG)

    override fun serialize(encoder: Encoder, value: LocalDateTime) {
        encoder.encodeLong(value.toEpochSecond(ZoneOffset.UTC))
    }

    override fun deserialize(decoder: Decoder): LocalDateTime {
        val seconds = decoder.decodeLong()
        return LocalDateTime.ofEpochSecond(seconds, 0, ZoneOffset.UTC)
    }
}

//class CustomIdController : IdController {
//    override fun findIdProperty(type: KClass<*>): KProperty1<*, *>? {
//        if (type == BaseDbModel::class) {
//            return type.declaredMemberProperties.find { it.name == "id" }
//                ?: type.memberProperties.find { it.name == "id" }
//        }
//        return super.findIdProperty(type)
//    }
//
//    override fun <T, R> getIdValue(idProperty: KProperty1<T, R>, instance: T): R? {
//        if (instance is BaseDbModel<*>) {
//            return
//            idProperty.
//        }
//        return super.getIdValue(idProperty, instance)
//    }
//
//    override fun <T, R> setIdValue(idProperty: KProperty1<T, R>, instance: T) {
//        super.setIdValue(idProperty, instance)
//    }
//}