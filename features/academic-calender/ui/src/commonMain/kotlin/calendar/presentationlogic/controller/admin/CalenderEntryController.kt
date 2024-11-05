package calendar.presentationlogic.controller.admin

import calendar.presentationlogic.controller.core.ICoreController
import feature.academiccalender.domain.model.AcademicCalender

interface CalenderEntryController:ICoreController {
   suspend fun insert(calender: AcademicCalender)
   suspend fun update(calender: AcademicCalender)
}