package data

import component.JsonParser
import data.data_source.holidayJson
import data.schema.CalendarWrapperSchema
import factory.NetworkFactory
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.fail

class JsonParserTest {

    private val jsonParser: JsonParser = NetworkFactory.createJsonParser()

    @Test
    fun testParseCalendar() {
        val fileContent = holidayJson
        val result = jsonParser.parse(fileContent, CalendarWrapperSchema.serializer())
        result.onSuccess { calendar ->
            println(calendar)
            assertEquals(2024, calendar.academicCalendar.year, "The year should be 2024")
            assertTrue(calendar.academicCalendar.januaryHolidays.isNotEmpty(), "January holidays should not be empty")
        }.onFailure { error ->
            fail("Parsing failed with error: ${error.message}")
        }
    }
}
