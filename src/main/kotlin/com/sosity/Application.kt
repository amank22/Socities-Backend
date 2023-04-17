package com.sosity

import com.sosity.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) =
    embeddedServer(Netty, port = System.getProperty("PORT").toIntOrNull()?:8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureHTTP()
    configureSecurity()
    configureSerialization()
    configureRouting()
    configureDatabases()
    configureMonitoring()
}
