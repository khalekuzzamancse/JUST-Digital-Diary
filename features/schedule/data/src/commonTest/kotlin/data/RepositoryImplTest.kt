package data


import kotlinx.coroutines.runBlocking
import schedule.data.factory.DataModuleFactory
import kotlin.test.Test
import kotlin.test.fail

class RepositoryImplTest {

    @Test
    fun testClassRoutine() {
        runBlocking {
            val response = DataModuleFactory.createRepository().retrieveClassSchedule("")
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
            val response = DataModuleFactory.createRepository().retrieveExamSchedule("")
            response.onSuccess {
                println(response.getOrNull())
            }.onFailure {
                fail()
            }

        }

    }


}