package data

import data.entity.AcademicCalenderEntity
import data.entity.HolidayEntity
import data.misc.CalendarBuilder
import data.misc.HodidayAdder
import kotlin.test.Test
import kotlin.test.assertEquals

class HodidayAdderTest {

    private val updater = HodidayAdder()

    @Test
    fun `test update calendar model with valid holidays`() {
        val calendarModel =CalendarBuilder().build(2024)
        val academicCalendar = AcademicCalenderEntity(
            year = 2024,
            holidays = listOf(
                listOf(
                    HolidayEntity(day = 1, type = HolidayEntity.ALL_OFF, reason = "New Year's Day"),
                    HolidayEntity(day = 25, type = HolidayEntity.SPECIAL_DAY, reason = "University Holiday")
                )
            )
        )

        val updatedCalendarModel = updater.add(calendarModel, academicCalendar)

        // Expected result
        val expectedCalendarModel=CalendarBuilder().build(2024)

        // Assert that the updated calendar model matches the expected one
        assertEquals(expectedCalendarModel, updatedCalendarModel)
    }

    @Test
    fun `test update calendar model with invalid holiday type`() {
        val calendarModel =CalendarBuilder().build(2024)

        val academicCalendar = AcademicCalenderEntity(
            year = 2024,
            holidays = listOf(
                listOf(
                    HolidayEntity(day = 1, type = 999, reason = "Invalid Holiday") // Invalid holiday type
                )
            )
        )

        val updatedCalendarModel = updater.add(calendarModel, academicCalendar)

        // Expected result: Since the holiday type is invalid, the calendar model should remain unchanged
        val expectedCalendarModel =CalendarBuilder().build(2024)

        // Assert that the updated calendar model matches the expected one
        assertEquals(expectedCalendarModel, updatedCalendarModel)
    }

    @Test
    fun `test update calendar model with mixed valid and invalid holidays`() {
        val calendarModel=CalendarBuilder().build(2024)

        val academicCalendar = AcademicCalenderEntity(
            year = 2024,
            holidays = listOf(
                listOf(
                    HolidayEntity(day = 1, type = HolidayEntity.ALL_OFF, reason = "New Year's Day"),
                    HolidayEntity(day = 21, type = 999, reason = "Invalid Holiday") // Invalid holiday type
                ),
                listOf(
                    HolidayEntity(day = 14, type = HolidayEntity.SPECIAL_DAY, reason = "Valentine's Day")
                )
            )
        )

        val updatedCalendarModel = updater.add(calendarModel, academicCalendar)

        // Expected result
        val expectedCalendarModel =CalendarBuilder().build(2024)

        // Assert that the updated calendar model matches the expected one
        assertEquals(expectedCalendarModel, updatedCalendarModel)
    }
}
