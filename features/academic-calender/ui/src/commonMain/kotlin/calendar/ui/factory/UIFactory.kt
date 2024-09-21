package calendar.ui.factory

import calendar.ui.editor.HolidayEditorController
import calendar.ui.viewer.CalendarViewController

object UIFactory {
    fun createCalenderController(): CalendarViewController =
        CalendarViewControllerImpl( viewerController = CalendarControllerImpl())
    fun createCalenderEditorController(): HolidayEditorController =
        EditorControllerImpl(
            selectionController = SelectionControllerImpl(),
            viewerController = CalendarControllerImpl()
        )
}