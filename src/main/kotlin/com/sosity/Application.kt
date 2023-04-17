package com.sosity

import io.ktor.server.application.*
import com.sosity.plugins.*
import io.ktor.server.netty.*

fun main(args: Array<String>): Unit =
    EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureHTTP()
    configureSecurity()
    configureSerialization()
    configureRouting()
    configureDatabases()
    configureMonitoring()
}
