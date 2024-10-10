package backend

import backend.apis.departmentRoutes
import backend.apis.facultyRoutes
import backend.database.FacultyRepository
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import kotlinx.coroutines.runBlocking

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        module()
        runBlocking {
            addFaculties()
        }
    }.start(wait = true)

}

suspend fun addFaculties() {
    val faculties = listOf(
        FacultyRepository.Companion.Faculty(
            1,
            "FET001",
            "Faculty of Engineering and Technology",
            5
        ),
        FacultyRepository.Companion.Faculty(
            2,
            "FAST002",
            "Faculty of Applied Science and Technology",
            4
        ),
        // Add more faculties here...
    )
    println(FacultyRepository.getAllFaculties())

//    for (faculty in faculties) {
//        FacultyRepository.addFaculty(faculty)
//    }
}

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }
    facultyRoutes()
    departmentRoutes()
}
