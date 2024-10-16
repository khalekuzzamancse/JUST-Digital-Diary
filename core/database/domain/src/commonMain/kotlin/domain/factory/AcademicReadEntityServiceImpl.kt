@file:Suppress("unused")
package domain.factory

import domain.entity.academic.DepartmentReadEntity
import domain.entity.academic.FacultyReadEntity
import domain.entity.academic.TeacherReadEntity
import domain.service.AcademicReadEntityService
import kotlinx.serialization.json.Json

class AcademicReadEntityServiceImpl : AcademicReadEntityService {
    private val parser = Json {
        ignoreUnknownKeys = true // Filters the unknown keys such as properties that do not exist in the read entity
        prettyPrint = true
    }

    override fun parseAsFacultyOrThrow(json: String): String {
        val entity = parser.decodeFromString(FacultyReadEntity.serializer(), json)
        return parser.encodeToString(FacultyReadEntity.serializer(), entity)
    }

    override fun parseAsDeptOrThrow(json: String): String {
        val entity = parser.decodeFromString(DepartmentReadEntity.serializer(), json)
        return parser.encodeToString(DepartmentReadEntity.serializer(), entity)
    }

    override fun parseAsTeacherOrThrow(json: String): String {
        val entity = parser.decodeFromString(TeacherReadEntity.serializer(), json)
        return parser.encodeToString(TeacherReadEntity.serializer(), entity)
    }
}
