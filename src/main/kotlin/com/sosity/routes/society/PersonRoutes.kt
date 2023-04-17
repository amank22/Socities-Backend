package com.sosity.routes.society

import com.mongodb.MongoWriteException
import com.sosity.helpers.jsonInstance
import com.sosity.models.*
import com.sosity.routes.society.models.BaseRequest
import com.sosity.routes.society.tables.Person
import com.sosity.routes.society.utils.PersonSave
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.resources.post
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*
import kotlinx.serialization.decodeFromString

fun Routing.personRoutes() {
    get<PersonResource> { quries ->
        call.respond(Response.Success(PersonSchema.get(quries.batchSize, quries.page, quries.full)))
    }
    post<PersonResource> {
        val person = getPerson()
        if (person == null) {
            call.respond(BodyError("Body is not valid. Please check with api team"))
            return@post
        }
        try {
            PersonSave.pdf(person)
            val insertedId = PersonSchema.insert(person).insertedId?.asObjectId()?.value?.toString().orEmpty()
            call.respond(
                HttpStatusCode.Created,
                Response.Success(insertedId)
            )
        } catch (e: MongoWriteException) {
            e.printStackTrace()
            call.respond(
                HttpStatusCode.Conflict,
                Response.Failure(
                    DuplicateFound(msg = "Same person already exists")
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            call.respond(
                HttpStatusCode.InternalServerError,
                Response.Failure(
                    GenericException(e.message.orEmpty())
                )
            )
        }
    }
    get<PersonResource.Id> { person ->
        val p = PersonSchema.get(person.id)
        if (p == null) {
            call.respond(HttpStatusCode.BadRequest, Response.Failure(ContentNotFound("No person with this id found")))
        } else {
            call.respond(HttpStatusCode.OK, Response.Success(p))
        }
    }
    post<PersonResource.Id> { person ->
        val dbPerson = PersonSchema.get(person.id)
        if (dbPerson == null) {
            call.respond(HttpStatusCode.BadRequest, Response.Failure(ContentNotFound("No person with this id found")))
            return@post
        }
        val bodyPerson = getPerson()
        if (bodyPerson == null) {
            call.respond(HttpStatusCode.BadRequest, Response.Failure(BodyError("Could not parse body to person")))
            return@post
        }
        val result = PersonSchema.update(dbPerson.id, bodyPerson)
        if (result.modifiedCount <= 0) {
            call.respond(
                HttpStatusCode.InternalServerError,
                Response.Failure(GenericException("Error in updating database"))
            )
            return@post
        }
        call.respond(HttpStatusCode.OK, Response.Success(Pair("modified", true)))
    }
    post<PersonResource.Generate> { person ->
        val p = PersonSchema.get(person.id)
        if (p == null) {
            call.respond(HttpStatusCode.NoContent, Response.Failure(ContentNotFound("No person with this id found")))
        } else {
            PersonSave.pdf(p)
            call.respond(Response.Success(p))
        }
    }
}

private suspend fun PipelineContext<Unit, ApplicationCall>.getPerson() =
    try {
        val str = call.receiveText()
        jsonInstance.decodeFromString<BaseRequest<Person>>(str)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }?.data