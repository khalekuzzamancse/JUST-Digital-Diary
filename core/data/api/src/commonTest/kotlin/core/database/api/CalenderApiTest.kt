package core.database.api

import core.database.factory.ApiFactory
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

            try {
                println(api.insert(json))
            } catch (e: Exception) {
                fail("Insert failed: ${e.message}")
            }
        }
    }

    @Test
    fun testReadAllAcademicCalendars() {
        executeTest {
            try {
                val result = api.readAll()
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
                  "year": 2025,
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
                println(api.update(year = 2025, calender = json))
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
