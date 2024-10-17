

package data.factory

import data.entity.AcademicCalenderEntity
import data.entity.HolidayEntity
import feature.academiccalender.domain.model.AcademicCalender2
import feature.academiccalender.domain.model.HolidayType

internal object ModelMapper {


    fun AcademicCalender2.toEntity(): AcademicCalenderEntity {
        return AcademicCalenderEntity(
            year = this.year,
            holidays = this.holiday.map { monthHolidays ->
                monthHolidays.map { holiday ->
                    HolidayEntity(
                        day = holiday.day,
                        type = holiday.holidayType.toEntityType(),
                        reason = holiday.reason
                    )
                }
            }
        )
    }

    /**
     * Extension function to map HolidayType to its corresponding integer type for HolidayEntity.
     */
    private fun HolidayType.toEntityType(): Int {
        return when (this) {
            HolidayType.AllOff -> HolidayEntity.ALL_OFF
            HolidayType.OnlyClassOff -> HolidayEntity.ONLY_CLASS_OFF
            HolidayType.SpecialDay -> HolidayEntity.SPECIAL_DAY
        }
    }





}