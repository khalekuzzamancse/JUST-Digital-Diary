package data

import core.database.api.ApiFactory
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.fail

class CalenderApiTest {
    private val api = ApiFactory.calenderApi()

    @Test
    fun testInsertAcademicCalender() {
        executeTest {
            val json = """
                {
                  "year": 2024,
                  "holidays": [
                    [{"day": 1, "type": 1, "reason": "New Year's Day"}],
                    [{"day": 21, "type": 2, "reason": "University Holiday"}],
                    [],
                    [],
                    [],
                    [],
                    [],
                    [],
                    [],
                    [],
                    [],
                    [],
                    []
                  ]
                }
            """.trimIndent()

            println(api.insert(json))

        }
    }

    @Test
    fun testReadAllAcademicCalendars() {
        executeTest {
            try {
                val result = api.readOfCurrentYear()
                println("All calendars: $result")
            } catch (e: Exception) {
                fail("Reading all calendars failed: ${e.message}")
            }
        }
    }

    @Test
    fun testReadCurrentYearAcademicCalendar() {
        executeTest {
            try {
                val result = api.readOfCurrentYear()
                println("Current year calendar: $result")
            } catch (e: Exception) {
                fail("Reading current year calendar failed: ${e.message}")
            }
        }
    }

    @Test
    fun testUpdateAcademicCalendar() {
        executeTest {
            val json = """
                {
                  "year": 2024,
                  "holidays": [
                    [{"day": 2, "type": 1, "reason": "Updated Holiday"}],
                    [{"day": 21, "type": 2, "reason": "University Holiday"}],
                    [],
                    [],
                    [],
                    [],
                    [],
                    [],
                    [],
                    [],
                    [],
                    []
                  ]
                }
            """.trimIndent()

            try {
                api.update(year = 2024, calender = json)
                println("Update successful!")
            } catch (e: Exception) {
                fail("Update failed: ${e.message}")
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