package di


import schedule.data.factory.DataFactory
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.fail

class RepositoryImplTest {

    @Test
    fun testClassRoutine() {
        runBlocking {
            val response = DataFactory.repository().retrieveClassSchedule()
            response.onSuccess {
                println(response.getOrNull())
            }.onFailure {
                fail()
            }

        }

    }
    @Test
    fun testExamRoutine() {
        runBlocking {
            val response = DataFactory.repository().retrieveExamSchedule("")
            response.onSuccess {
                println(response.getOrNull())
            }.onFailure {
                fail()
            }

        }

    }


}