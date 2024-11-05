@file:Suppress("unused")
package domain.factory

import domain.core.toInsertionResult
import core.data.entity.calender.*
import domain.model.InsertionResult
import domain.service.CalenderInsertionService

/**
 * - Read the docs of [CalenderInsertionService]
 */
class CalenderInsertionServiceImpl internal  constructor(): CalenderInsertionService {
    override fun processWriteEntityOrThrow(json: String): InsertionResult {
        val faculty =
            ContractFactory.jsonParser().parseOrThrow(json, AcademicCalenderEntity.serializer())

        //Execution is here means parse successful so it a valid json
        // Each calender must belong to a single year, year can be used as primary key
        val pk = faculty.year.toString()

        return toInsertionResult(json = json, fields = emptyList(), primaryKey = pk)
    }
}