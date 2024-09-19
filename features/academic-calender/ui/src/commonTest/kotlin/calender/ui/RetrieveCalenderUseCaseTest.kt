package calender.ui
import di.DIFactory
import kotlin.test.Test
import kotlin.test.fail

class RetrieveCalenderUseCaseTest {
    @Test
    fun retrieveCalender(){
        val useCase=DIFactory.createRetrieveCalenderUseCase()
        useCase.execute(2014)
            .onSuccess {calender->
                println(calender)
            }
            .onFailure {exception->
                fail(exception.toString())
            }

    }
    @Test
    fun _1stMonthTest(){
        val useCase=DIFactory.createRetrieveCalenderUseCase()
        useCase.execute(2014)
            .onSuccess {calender->
                println(calender.months.first())
            }
            .onFailure {exception->
                fail(exception.toString())
            }

    }


}