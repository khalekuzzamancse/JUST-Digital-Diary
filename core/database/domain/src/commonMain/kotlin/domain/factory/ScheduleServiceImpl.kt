@file:Suppress("unused")

package domain.factory

import domain.core.EntityExtraField
import domain.core.toInsertionResult
import domain.entity.schedule.ClassScheduleReadEntity
import domain.entity.schedule.ClassScheduleWriteEntity
import domain.model.InsertionResult
import domain.service.ScheduleService
import kotlinx.serialization.json.Json

class ScheduleServiceImpl : ScheduleService {
    override fun processAsClassScheduleWriteEntityOrThrow(
        deptId: String,
        json: String
    ): InsertionResult {
        val entity =
            ContractFactory.jsonParser().parseOrThrow(json, ClassScheduleWriteEntity.serializer())

        //Execution is here means parse successful so it a valid json
        // Each schedule unique of combination=deptId+year+semester+session
        val pk = with(entity) { deptId + year + semester + session }
        //Need to add the id, because client is now allowed to write it

        val extraFields = listOf(
            ClassScheduleReadEntity::id.name to pk,//Keeping id and primary are the same
            EntityExtraField.SCHEDULE_ENTITY_FIELD_DEPT_ID to deptId
        )

        return toInsertionResult(json = json, fields = extraFields, primaryKey = pk)
    }

    override fun parseAsClassScheduleReadEntityOrThrow(json: String): String {
        val parser = Json {
            ignoreUnknownKeys =
                true // Filters the unknown keys such as properties that do not exist in the read entity
            prettyPrint = true
        }

        val entity = parser.decodeFromString(
            ClassScheduleReadEntity.serializer(),
            json
        )//Filter the extra fields from the json that is not defined in Read entity
        return parser.encodeToString(ClassScheduleReadEntity.serializer(), entity)

    }
}