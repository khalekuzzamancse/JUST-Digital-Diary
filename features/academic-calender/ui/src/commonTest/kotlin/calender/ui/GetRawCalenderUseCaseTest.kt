package calender.ui
import di.DIFactory
import domain.model.DayNameModel
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.fail

class GetRawCalenderUseCaseTest {
    @Test
    fun retrieveCalender(){
       runBlocking {
           val useCase=DIFactory.createRawRetrieveCalenderUseCase()
           useCase.execute(2024, listOf(DayNameModel.THURSDAY,DayNameModel.THURSDAY))
               .onSuccess {calendar->
                   println(calendar)
               }
               .onFailure {exception->
                   fail(exception.toString())
               }
       }


    }

}