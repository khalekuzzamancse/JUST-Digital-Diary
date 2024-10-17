package calendar.presentationlogic.controller.admin

import calendar.presentationlogic.controller.core.ICoreController
import feature.academiccalender.domain.model.AcademicCalender2

interface CalenderEntryController:ICoreController {
   suspend fun insert(calender: AcademicCalender2)
   suspend fun update(calender: AcademicCalender2)
}