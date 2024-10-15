package calendar.ui
import di.DIFactory
import feature.academiccalender.domain.model.DayOfWeek
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.fail

class ReadRawCalenderUseCaseTest {
    @Test
    fun retrieveCalender(){
       runBlocking {
           val useCase=DIFactory.readRawCalenderUseCase()
           useCase.execute(2024, listOf(DayOfWeek.THURSDAY, DayOfWeek.THURSDAY))
               .onSuccess {calendar->
                   println(calendar)
               }
               .onFailure {exception->
                   fail(exception.toString())
               }
       }


    }

}