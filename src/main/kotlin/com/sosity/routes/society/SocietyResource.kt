package com.sosity.routes.society

import com.sosity.routes.BaseApiResource
import io.ktor.resources.*
import kotlinx.serialization.Serializable

@Serializable
@Resource("/society")
class SocietyResource(val parent : BaseApiResource = BaseApiResource()) {
    @Serializable
    @Resource("new")
    class New(val parent: SocietyResource = SocietyResource())

    @Serializable
    @Resource("{id}")
    class Id(val parent: SocietyResource = SocietyResource(), val id: Long)
}