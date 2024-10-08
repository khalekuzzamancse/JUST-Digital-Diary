package backend.apis
import backend.entity.DepartmentEntity
import backend.entity.FacultyDepartmentsEntity
import backend.model.DepartmentListModel
import backend.model.DepartmentModel
import backend.services.FileLoader
import backend.services.logger
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json



fun Application.departmentRoutes() {
    val path = "E:\\projectes\\kmp_projects\\JUSTDigitalDiary\\backend\\data\\departments.json"
    val fileLoader = FileLoader(path)

    routing {
        // GET route: Retrieve departments by faculty ID
        get("/departments/{facultyId}") {
            val facultyId = call.parameters["facultyId"]

            if (facultyId != null) {
                val departmentDataJson = fileLoader.loadFromFile()

                if (departmentDataJson != null) {
                    try {
                        val departmentData: Map<String, FacultyDepartmentsEntity> = Json.decodeFromString(departmentDataJson)

                        if (departmentData.containsKey(facultyId)) {
                            val facultyDepartments = departmentData[facultyId]
                            if (facultyDepartments != null) {
                                // Convert the departments into the DepartmentModel
                                val departments = facultyDepartments.departments.map {
                                    DepartmentModel(
                                        id = it.id,
                                        deptId = it.deptId,
                                        name = it.name,
                                        shortName = it.shortName,
                                        membersCount = getDummyMembersCount() // Dummy member count function
                                    )
                                }

                                // Create a DepartmentListModel with a dummy faculty name
                                val departmentList = DepartmentListModel(
                                    facultyName = "Unnamed Faculty",  // Dummy faculty name
                                    departments = departments
                                )

                                // Log the success and return the transformed response
                                logger.info("Successfully retrieved departments for faculty ID: $facultyId")
                                call.respond(departmentList)
                            } else {
                                logger.warn("No departments found for faculty ID: $facultyId")
                                call.respondText("No departments found for faculty ID: $facultyId", status = HttpStatusCode.NotFound)
                            }
                        } else {
                            logger.warn("Faculty ID not found: $facultyId")
                            call.respondText("Faculty ID not found", status = HttpStatusCode.NotFound)
                        }

                    } catch (e: Exception) {
                        logger.error("Error parsing the JSON data for faculty ID: $facultyId", e)
                        call.respondText("Error parsing the JSON data", status = HttpStatusCode.InternalServerError)
                    }
                } else {
                    logger.error("Error loading the department data from file: $path")
                    call.respondText("Error loading the department data", status = HttpStatusCode.InternalServerError)
                }
            } else {
                logger.warn("Faculty ID is missing in the request")
                call.respondText("Faculty ID is missing", status = HttpStatusCode.BadRequest)
            }
        }
        post("/add/departments/{facultyId}") {
            val facultyId = call.parameters["facultyId"]
            val newDepartmentModel = call.receive<DepartmentModel>()

            if (facultyId != null) {
                val departmentDataJson = fileLoader.loadFromFile()

                if (departmentDataJson != null) {
                    try {
                        val departmentData: MutableMap<String, FacultyDepartmentsEntity> = Json.decodeFromString(departmentDataJson)

                        if (departmentData.containsKey(facultyId)) {
                            // Get the existing departments and convert newDepartmentModel to DepartmentEntity
                            val currentDepartments = departmentData[facultyId]?.departments?.toMutableList() ?: mutableListOf()

                            val newDepartmentEntity = DepartmentEntity(
                                id = newDepartmentModel.id,
                                deptId = newDepartmentModel.deptId,
                                name = newDepartmentModel.name,
                                shortName = newDepartmentModel.shortName
                            )

                            // Add the new department to the list
                            currentDepartments.add(newDepartmentEntity)

                            // Update the faculty's departments list
                            departmentData[facultyId] = FacultyDepartmentsEntity(departments = currentDepartments)

                            // Save the updated data back to the file
                            val updatedJson = Json.encodeToString(departmentData)
                            fileLoader.saveToFile(updatedJson)

                            logger.info("Successfully added department to faculty ID: $facultyId")
                            call.respondText("Department added successfully", status = HttpStatusCode.Created)
                        } else {
                            logger.warn("Faculty ID not found: $facultyId")
                            call.respondText("Faculty ID not found", status = HttpStatusCode.NotFound)
                        }

                    } catch (e: Exception) {
                        logger.error("Error parsing the JSON data for faculty ID: $facultyId", e)
                        call.respondText("Error parsing the JSON data", status = HttpStatusCode.InternalServerError)
                    }
                } else {
                    logger.error("Error loading the department data from file: $path")
                    call.respondText("Error loading the department data", status = HttpStatusCode.InternalServerError)
                }
            } else {
                logger.warn("Faculty ID is missing in the request")
                call.respondText("Faculty ID is missing", status = HttpStatusCode.BadRequest)
            }
        }


    }
}

// Dummy function to generate members count
fun getDummyMembersCount(): Int {
    return (5..30).random()  // Generates a random members count between 5 and 30
}
