package calendar.ui
import acdemiccalender.di.DiContainer
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.fail

class RetrieveCalenderUseCaseTest {
    @Test
    fun retrieveCalender(){
        runBlocking {
            val useCase= DiContainer.readAcademicCalender()
            useCase.execute()
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
            val useCase= DiContainer.readAcademicCalender()
            useCase.execute()
                .onSuccess {calender->
                    println(calender.months.first())
                }
                .onFailure {exception->
                    fail(exception.toString())
                }
        }

    }


}