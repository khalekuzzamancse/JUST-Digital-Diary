@file:Suppress("unused")
package domain.factory

import domain.entity.DepartmentReadEntity
import domain.entity.FacultyReadEntity
import domain.entity.TeacherReadEntity
import domain.service.ReadEntityParserService
import kotlinx.serialization.json.Json

class ReadEntityParserServiceImpl : ReadEntityParserService {
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
