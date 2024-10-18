package server

import core.database.factory.ApiFactory
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun Application.facultyRoutes() {

    routing {
        get("/faculty") {
            try {
            //    call.respondText("faculty")
                val jsonResponse = ApiFactory.academicApi().readAllFaculty()
                call.respondText(jsonResponse, ContentType.Application.Json)
            } catch (e: Exception) {
                // Handle the exception appropriately
                call.respond(HttpStatusCode.InternalServerError, "An error occurred")
            }

        }

    }
}