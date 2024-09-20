package calender.factory

import calender.interface_adapter.CalenderControllerImpl
import calender.ui.calender.CalendarController

object UIFactory {
    fun createCalenderController():CalendarController=
        CalenderControllerImpl()
}