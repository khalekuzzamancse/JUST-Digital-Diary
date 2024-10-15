package data

import core.database.api.ApiFactory
import data.entity.AcademicCalenderEntity
import data.misc.CalendarBuilder
import data.misc.HodidayAdder
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.fail

class RepositoryTest {
    private val api = ApiFactory.calenderApi()

    @Test
    fun convertToEntityTest() {
        executeTest {
            try {
                val json = api.readOfCurrentYear()
                val entity=Json.decodeFromString(AcademicCalenderEntity.serializer(),json)
                println("Current year calendar: $entity")
            } catch (e: Exception) {
                fail("Reading current year calendar failed: ${e.message}")
            }
        }
    }
    @Test
    fun addHolidayToRawCalenderTest() {
        executeTest {
            try {
                val json = api.readOfCurrentYear()
                println("holidays: $json")
                println("--------")
                val entity=Json.decodeFromString(AcademicCalenderEntity.serializer(),json)
                val rawCalender=CalendarBuilder().build(entity.year)
                val academicCalender=HodidayAdder().add(rawCalender,entity)

                academicCalender.months.forEach {
                    println(it.month.name)
                    println(it.days.first { it.isHoliday })
                   // println(it.days)
                    println("-------------")
                }
            } catch (e: Exception) {
                fail("Reading current year calendar failed: ${e.message}")
            }
        }
    }

    /**
     * Helper method to execute tests with error handling.
     */
    private fun executeTest(block: suspend () -> Unit) {
        runBlocking {
            try {
                block()
            } catch (e: Throwable) {
                fail("Test failed due to: $e")
            }
        }
    }
}