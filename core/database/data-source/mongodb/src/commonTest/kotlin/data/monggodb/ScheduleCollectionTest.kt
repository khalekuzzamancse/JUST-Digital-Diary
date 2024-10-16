package data.monggodb

import data.monggodb.db.CalenderCollection
import data.monggodb.db.ScheduleCollection
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.fail

class ScheduleCollectionTest {

    private val collection = ScheduleCollection()
    private val deptId="cse"

    @Test
    fun testInsertAcademicCalender() {
        executeTest {
            val json = """
         {
  "session": "2023-2024",
  "year": "4",
  "semester": "2",
  "routine": [
    {
      "day": "Mon",
      "items": [
        {
          "subject": "Mathematics",
          "time": "10:30",
          "teacher": "Dr. John Doe"
        },
        {
          "subject": "Physics",
          "time": "12:00",
          "teacher": "Prof. Jane Smith"
        }
      ]
    },
    {
      "day": "Tue",
      "items": [
        {
          "subject": "Chemistry",
          "time": "09:00",
          "teacher": "Dr. Alice Johnson"
        },
        {
          "subject": "Biology",
          "time": "11:30",
          "teacher": "Prof. Robert Brown"
        }
      ]
    }
  ]
}

            """.trimIndent()

            try {
                println(collection.insert(deptId = deptId, json = json))
            } catch (e: Exception) {
                fail("Insert failed: ${e.message}")
            }
        }
    }

    @Test
    fun testReadAllAcademicCalendars() {
        executeTest {
            try {
                val result = collection.readAll()
                println(result)
            } catch (e: Exception) {
                fail("Reading all calendars failed: ${e.message}")
            }
        }
    }

    @Test
    fun testReadCurrentYearAcademicCalendar() {
        executeTest {
            try {
                val result = collection.readByDept(deptId)
                println(result)
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
                println(collection.updateOrThrow(id = "", json = json))
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
