package com.sosity.plugins

import com.sosity.routes.society.personRoutes
import com.sosity.routes.society.societyRoutes
import com.sosity.routes.society.utilityRoutes
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }

    install(Resources)
    routing()
}

private fun Application.routing() {
    routing {
        // Static plugin. Try to access `/static/index.html`
        static {
            resource("/", "static/index.html")
//            resource("*", "static/index.html")
            static("static") {
                resources("static/files")
            }
        }
        get("api") {
            call.respond(mapOf("status" to true))
        }
        get("api/*") {
            call.respond(mapOf("status" to true))
        }

        societyRoutes()
        personRoutes()
        utilityRoutes()
    }
}
