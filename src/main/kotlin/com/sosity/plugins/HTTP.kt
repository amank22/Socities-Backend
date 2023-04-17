package com.sosity.plugins

import io.ktor.server.plugins.swagger.*
import io.ktor.server.routing.*
import io.ktor.server.plugins.partialcontent.*
import io.ktor.server.plugins.openapi.*
import io.ktor.server.plugins.defaultheaders.*
import io.ktor.server.plugins.conditionalheaders.*
import io.ktor.server.plugins.cachingheaders.*
import io.ktor.http.content.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.doublereceive.*

fun Application.configureHTTP() {
//    install(PartialContent) {
//            // Maximum number of ranges that will be accepted from a HTTP request.
//            // If the HTTP request specifies more ranges, they will all be merged into a single range.
//            maxRangeCount = 10
//        }
    install(DoubleReceive)
//    routing {
//        openAPI(path = "openapi")
//    }
//    routing {
//        swaggerUI(path = "openapi")
//    }
    install(DefaultHeaders) {
        header("X-Engine", "Ktor") // will send this header with each response
    }
//    install(ConditionalHeaders)
//    install(CachingHeaders) {
//        options { _, outgoingContent ->
//            when (outgoingContent.contentType?.withoutParameters()) {
//                ContentType.Text.CSS -> CachingOptions(CacheControl.MaxAge(maxAgeSeconds = 24 * 60 * 60))
//                else -> null
//            }
//        }
//    }
}
