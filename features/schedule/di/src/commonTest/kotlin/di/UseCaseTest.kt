package di


import kotlinx.coroutines.runBlocking
import schedule.di.DiFactory
import kotlin.test.Test
import kotlin.test.fail

class UseCaseTest {

    @Test
    fun classUseCaseTest() {
        runBlocking {
            val response = DiFactory.createRetrieveClassScheduleUseCase().execute()
            response.onSuccess {
                println(response.getOrNull())
            }.onFailure {
                fail()
            }

        }

    }
    @Test
    fun examUseCaseTest() {
        runBlocking {
            val response = DiFactory.createRetrieveExaScheduleUseCase().execute("")
            response.onSuccess {
                println(response.getOrNull())
            }.onFailure {
                fail()
            }

        }

    }


}