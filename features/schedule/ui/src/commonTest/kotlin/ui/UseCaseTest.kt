package ui


import schedule.di.DiContainer
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.fail

class UseCaseTest {

    @Test
    fun classUseCaseTest() {
        runBlocking {
            val response = DiContainer.readClassScheduleUseCase().execute()
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
            val response = DiContainer.readExaScheduleUseCase().execute("")
            response.onSuccess {
                println(response.getOrNull())
            }.onFailure {
                fail()
            }

        }

    }


}