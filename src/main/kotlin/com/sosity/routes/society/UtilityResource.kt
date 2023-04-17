package com.sosity.routes.society

import com.sosity.routes.BaseApiResource
import io.ktor.resources.*
import kotlinx.serialization.Serializable

@Serializable
@Resource("/utils")
class UtilityResource(val parent : BaseApiResource = BaseApiResource()) {
    @Serializable
    @Resource("index")
    class Index(val parent: UtilityResource = UtilityResource())

    @Serializable
    @Resource("status")
    class Status(val parent: UtilityResource = UtilityResource())
}