package com.sosity.routes.society

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.routing.post

fun Routing.societyRoutes() {
    get<SocietyResource> { societyResource ->
        // Get all articles ...
        call.respondText("List of articles sorted starting from $societyResource")
    }
    post<SocietyResource> {
        // Save an article ...
        call.respond("Yet to implement")
    }
    get<SocietyResource.Id> { article ->
        // Show an article with id ${article.id} ...
        call.respondText("An article with id ${article.id}", status = HttpStatusCode.OK)
    }
}