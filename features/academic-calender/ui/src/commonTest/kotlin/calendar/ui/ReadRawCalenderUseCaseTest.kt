package calendar.ui
import acdemiccalender.di.DiContainer
import feature.academiccalender.domain.model.DayOfWeek
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.fail

class ReadRawCalenderUseCaseTest {
    @Test
    fun retrieveCalender(){
       runBlocking {
           val useCase= DiContainer.readRawCalenderUseCase()
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