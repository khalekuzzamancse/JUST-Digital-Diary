package di


import kotlinx.coroutines.runBlocking
import schedule.di.DomainModuleFactory
import kotlin.test.Test
import kotlin.test.fail

class UseCaseTest {

    @Test
    fun classUseCaseTest() {
        runBlocking {
            val response = DomainModuleFactory.createRetrieveClassScheduleUseCase().execute("")
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
            val response = DomainModuleFactory.createRetrieveExaScheduleUseCase().execute("")
            response.onSuccess {
                println(response.getOrNull())
            }.onFailure {
                fail()
            }

        }

    }


}