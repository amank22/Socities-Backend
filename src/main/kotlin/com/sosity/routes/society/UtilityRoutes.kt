package com.sosity.routes.society

import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.utilityRoutes() {
    get<UtilityResource.Index> {
        val msg = PersonSchema.index()
        call.respond(mapOf("msg" to msg))

    }
}