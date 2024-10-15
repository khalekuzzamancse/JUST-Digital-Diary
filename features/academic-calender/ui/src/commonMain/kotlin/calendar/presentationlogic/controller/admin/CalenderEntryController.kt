package calendar.presentationlogic.controller.admin

import calendar.presentationlogic.controller.core.CoreController
import feature.academiccalender.domain.model.AcademicCalender2

interface CalenderEntryController:CoreController {
   suspend fun insert(calender: AcademicCalender2)
   suspend fun update(calender: AcademicCalender2)
}