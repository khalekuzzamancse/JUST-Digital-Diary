package calender.ui
import calender.ui.calender.nedw.CalendarGridBuilder
import di.DIFactory
import java.time.Month
import kotlin.test.Test
import kotlin.test.fail

class CalenderGridTest {
    @Test
    fun retrieveCalender(){
        val useCase=DIFactory.createRetrieveCalenderUseCase()
        useCase.execute(2014)
            .onSuccess {calendar->
                val january = calendar.months.first { it.month == Month.JANUARY }
                val januaryGrid =  CalendarGridBuilder().buildMonthGrid(january)

                januaryGrid
                    .cells
                    .sortedBy { it.cellNo }
                    .forEach { cell ->
                    println(cell)
                }
            }
            .onFailure {exception->
                fail(exception.toString())
            }


    }

}