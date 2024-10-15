package calendar.presentationlogic.controller.admin

import calendar.presentationlogic.controller.core.CoreController
import domain.model.AcademicCalender2

interface InsertCalenderController:CoreController {
   suspend fun insert(calender: AcademicCalender2)
}