@file:Suppress("unused")
package domain.service
import core.data.entity.calender.AcademicCalenderEntity

interface CalenderReadEntityService {

    /**
     * - Parse a [AcademicCalenderEntity] from the given JSON or throw an exception.
     * - It filters extra fields not present in [AcademicCalenderEntity] and returns the JSON version of the parsed entity.
     * @param json the raw JSON input to be parsed.
     * @return the parsed [AcademicCalenderEntity] in JSON format, ready to be sent to the client.
     * @throws Throwable if the JSON format is invalid or does not match [AcademicCalenderEntity].
     */
    fun parseOrThrow(json: String): String

}
