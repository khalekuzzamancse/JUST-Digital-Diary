@file:Suppress("spellCheckingInspection", "unused")

package data

import core.database.api.ApiFactory
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import schedule.data.entity.schedule.ClassScheduleReadEntity
import schedule.data.mapper.ReadEntityModelMapper
import kotlin.test.Test
import kotlin.test.fail

class ScheduleApiTest {
    private val api = ApiFactory.scheduleApi()
    private val parser = Json { ignoreUnknownKeys = true }
    private val deptId =
        "computerscienceandengineering"//TODO:Make sure dept exists in the department collection

    @Test
    fun insert() {
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
                println(api.insert(deptId = deptId, json = json))
            } catch (e: Exception) {
                fail("Insert failed: ${e.message}")
            }
        }
    }

    @Test
    fun readAll() {
        executeTest {
            try {
                val json = api.readAll()
                println("Json:$json")


            } catch (e: Exception) {
                fail(" ${e.message}")
            }
        }
    }

    @Test
    fun readAllEntityTest() {
        executeTest {
            val json = api.readAll()
            val entity = parser.decodeFromString(
                ListSerializer(ClassScheduleReadEntity.serializer()),
                json
            )
            println("Entity:$entity")
        }
    }

    @Test
    fun readAllEntityModelTest() {
        executeTest {
            val json = api.readAll()
            val model = with(ReadEntityModelMapper) {
                parser
                    .decodeFromString(ListSerializer(ClassScheduleReadEntity.serializer()), json)
                    .map { it.toModel() }
            }
            println("Model:$model")
        }
    }


    @Test
    fun readUnderDept() {
        executeTest {
            try {
                val result = api.readUnderDept(deptId)
                println(result)
            } catch (e: Exception) {
                fail(" failed: ${e.message}")
            }
        }
    }

    @Test
    fun update() {
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
                println(api.update(id = "", json = json))
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