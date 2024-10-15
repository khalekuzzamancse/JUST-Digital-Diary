@file:Suppress("unused")
package domain.factory

import domain.entity.AcademicCalenderEntity
import domain.service.CalenderReadEntityService
import kotlinx.serialization.json.Json

class CalenderReadEntityServiceImpl: CalenderReadEntityService {
    private val parser = Json {
        ignoreUnknownKeys = true // Filters the unknown keys such as properties that do not exist in the read entity
        prettyPrint = true
    }
    override fun parseOrThrow(json: String): String {
        val entity = parser.decodeFromString(AcademicCalenderEntity.serializer(), json)
        return parser.encodeToString(AcademicCalenderEntity.serializer(), entity)
    }
}