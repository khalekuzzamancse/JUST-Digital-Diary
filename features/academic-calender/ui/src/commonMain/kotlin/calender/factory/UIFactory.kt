package calender.factory

import calender.add_calender.HolidayEditorUiController
import calender.interface_adapter.CalenderControllerImpl
import calender.interface_adapter.HolidayEditorUiControllerImpl
import calender.ui.calender.CalendarController

object UIFactory {
    fun createCalenderController():CalendarController=
        CalenderControllerImpl()
    fun createCalenderEditorController(): HolidayEditorUiController =
        HolidayEditorUiControllerImpl()
}