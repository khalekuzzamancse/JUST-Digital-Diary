package di


import kotlinx.coroutines.runBlocking
import schedule.di.DiContainer
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