@file:Suppress("ClassName")
package backend.apis

import backend.services.FileLoader
import backend.services.logger
import backend.model.FacultyModel
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

fun Application.facultyRoutes() {
    val path="E:\\projectes\\kmp_projects\\JUSTDigitalDiary\\backend\\data\\faculties.json"
    val fileLoader = FileLoader(path)
    routing {
        get("/faculty") {
            val json = fileLoader.loadFromFile()

            if (json != null) {
                try {
                    val jsonObject = Json.parseToJsonElement(json).jsonObject
                    val facultyArray = jsonObject["faculty"]?.jsonArray
                    val facultyList = facultyArray?.let {
                        Json.decodeFromJsonElement(ListSerializer(FacultyModel.serializer()), it)
                    } ?: emptyList()
                    call.respond(facultyList)
                } catch (e: Exception) {
                    logger.error("Error parsing faculty data: {}", e.message)
                    call.respondText("Error parsing the faculty data", status = HttpStatusCode.InternalServerError)
                }
            } else {
                call.respondText("Error loading faculty data from file", status = HttpStatusCode.InternalServerError)
            }
        }

        post("add/faculty") {
            val newFaculty = call.receive<FacultyModel>()
            val json = fileLoader.loadFromFile()

            if (json != null) {
                try {
                    val jsonObject = Json.parseToJsonElement(json).jsonObject
                    val facultyArray = jsonObject["faculty"]?.jsonArray
                    val facultyList = facultyArray?.let {
                        Json.decodeFromJsonElement(ListSerializer(FacultyModel.serializer()), it)
                    } ?: emptyList()

                    val updatedFacultyList = facultyList + newFaculty
                    val updatedJson = Json.encodeToString(mapOf("faculty" to updatedFacultyList))
                    fileLoader.saveToFile(updatedJson)

                    call.respondText("Faculty added successfully", status = HttpStatusCode.Created)
                } catch (e: Exception) {
                    logger.error("Error updating faculty data: {}", e.message)
                    call.respondText("Error updating faculty data", status = HttpStatusCode.InternalServerError)
                }
            } else {
                call.respondText("Error loading faculty data from file", status = HttpStatusCode.InternalServerError)
            }
        }
    }
}




