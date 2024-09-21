package data

import data.misc.CalendarBuilder
import domain.model.DayOfWeek
import kotlin.test.Test

class CalenderGeneratorTest {
    @Test
    fun  test(){

        val calendarBuilder = CalendarBuilder()
        val calendar = calendarBuilder
            .addWeekend(DayOfWeek.THURSDAY)
            .addWeekend(DayOfWeek.FRIDAY)
            .build(2024)

        println(calendar)
    }
}