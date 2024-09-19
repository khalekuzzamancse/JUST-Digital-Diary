package data

import data.misc.CalendarBuilder
import domain.model.DayNameModel
import kotlin.test.Test

class CalenderGeneratorTest {
    @Test
    fun  test(){

        val calendarBuilder = CalendarBuilder()
        val calendar = calendarBuilder
            .addWeekend(DayNameModel.THURSDAY)
            .addWeekend(DayNameModel.FRIDAY)
            .build(2024)

        println(calendar)
    }
}