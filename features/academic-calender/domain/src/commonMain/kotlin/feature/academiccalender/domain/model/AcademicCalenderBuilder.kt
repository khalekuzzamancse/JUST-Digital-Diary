@file:Suppress("UnUsed")
package feature.academiccalender.domain.model

import common.docs.domain_layer.ModelDoc


/**
 * Further discussion on:
- `Model`: see [ModelDoc]
 */

class AcademicCalendar private constructor(
    val year: Int,
    val januaryHolidays: List<Holiday>,
    val februaryHolidays: List<Holiday>,
    val marchHolidays: List<Holiday>,
    val aprilHolidays: List<Holiday>,
    val mayHolidays: List<Holiday>,
    val juneHolidays: List<Holiday>,
    val julyHolidays: List<Holiday>,
    val augustHolidays: List<Holiday>,
    val septemberHolidays: List<Holiday>,
    val octoberHolidays: List<Holiday>,
    val novemberHolidays: List<Holiday>,
    val decemberHolidays: List<Holiday>
) {
    /**
     * - The `Builder pattern` is used here to construct the `AcademicCalendar` object, which has many optional fields
     * (holidays for each month).
     * - This avoids a "monster" constructor and provides flexibility, allowing the user to initialize only the months they need
     * - The Builder ensures immutability, improves readability, and allows for easier maintenance when new fields are added.
     */

    class Builder(private val year: Int) {
        private var januaryHolidays: List<Holiday> = emptyList()
        private var februaryHolidays: List<Holiday> = emptyList()
        private var marchHolidays: List<Holiday> = emptyList()
        private var aprilHolidays: List<Holiday> = emptyList()
        private var mayHolidays: List<Holiday> = emptyList()
        private var juneHolidays: List<Holiday> = emptyList()
        private var julyHolidays: List<Holiday> = emptyList()
        private var augustHolidays: List<Holiday> = emptyList()
        private var septemberHolidays: List<Holiday> = emptyList()
        private var octoberHolidays: List<Holiday> = emptyList()
        private var novemberHolidays: List<Holiday> = emptyList()
        private var decemberHolidays: List<Holiday> = emptyList()

        fun januaryHolidays(holidays: List<Holiday>) = apply { this.januaryHolidays = holidays }
        fun februaryHolidays(holidays: List<Holiday>) = apply { this.februaryHolidays = holidays }
        fun marchHolidays(holidays: List<Holiday>) = apply { this.marchHolidays = holidays }
        fun aprilHolidays(holidays: List<Holiday>) = apply { this.aprilHolidays = holidays }
        fun mayHolidays(holidays: List<Holiday>) = apply { this.mayHolidays = holidays }
        fun juneHolidays(holidays: List<Holiday>) = apply { this.juneHolidays = holidays }
        fun julyHolidays(holidays: List<Holiday>) = apply { this.julyHolidays = holidays }
        fun augustHolidays(holidays: List<Holiday>) = apply { this.augustHolidays = holidays }
        fun septemberHolidays(holidays: List<Holiday>) = apply { this.septemberHolidays = holidays }
        fun octoberHolidays(holidays: List<Holiday>) = apply { this.octoberHolidays = holidays }
        fun novemberHolidays(holidays: List<Holiday>) = apply { this.novemberHolidays = holidays }
        fun decemberHolidays(holidays: List<Holiday>) = apply { this.decemberHolidays = holidays }

        fun build(): AcademicCalendar {
            return AcademicCalendar(
                year = year,
                januaryHolidays = januaryHolidays,
                februaryHolidays = februaryHolidays,
                marchHolidays = marchHolidays,
                aprilHolidays = aprilHolidays,
                mayHolidays = mayHolidays,
                juneHolidays = juneHolidays,
                julyHolidays = julyHolidays,
                augustHolidays = augustHolidays,
                septemberHolidays = septemberHolidays,
                octoberHolidays = octoberHolidays,
                novemberHolidays = novemberHolidays,
                decemberHolidays = decemberHolidays
            )
        }
    }
}
