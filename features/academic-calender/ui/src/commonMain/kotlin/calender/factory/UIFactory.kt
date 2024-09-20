package calender.factory

import calender.add_calender.EditorUiController
import calender.add_calender.SelectionControllerImpl
import calender.interface_adapter.CalenderControllerImpl
import calender.ui.calender.CalendarController

object UIFactory {
    fun createCalenderController():CalendarController=
        CalenderControllerImpl()
    fun createCalenderEditorController(): EditorUiController =
        EditorControllerImpl(
            selectionController = SelectionControllerImpl(),
            viewerController = CalendarControllerImpl()
        )
}