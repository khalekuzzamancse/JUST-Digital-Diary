package calendar.ui
import di.DIFactory
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.fail

class RetrieveCalenderUseCaseTest {
    @Test
    fun retrieveCalender(){
        runBlocking {
            val useCase=DIFactory.createAcademicRetrieveCalenderUseCase()
            useCase.execute(2014)
                .onSuccess {calender->
                    println(calender)
                }
                .onFailure {exception->
                    fail(exception.toString())
                }
        }


    }
    @Test
    fun _1stMonthTest(){
        runBlocking {
            val useCase=DIFactory.createAcademicRetrieveCalenderUseCase()
            useCase.execute(2014)
                .onSuccess {calender->
                    println(calender.months.first())
                }
                .onFailure {exception->
                    fail(exception.toString())
                }
        }

    }


}