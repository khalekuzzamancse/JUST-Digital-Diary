@file:Suppress("FunctionName")
package calendar.presentationlogic.factory

import calendar.presentationlogic.controller.admin.CalenderEntryController
import calendar.presentationlogic.factory.admin.CalenderEntryControllerImpl
import calendar.ui.admin.add_calender.AddHolidayCommand
import calendar.ui.admin.add_calender.AddHolidayCommandImpl
import calendar.ui.admin.add_calender.CalendarViewControllerImpl
import calendar.ui.admin.add_calender.HolidayDateSelector
import calendar.ui.admin.add_calender.HolidayEditorControllerImpl
import calendar.ui.admin.add_calender.HolidayDateSelectorImpl
import calendar.ui.public_.view_calender.CalendarUIGridModelPresenterImpl
import calendar.ui.public_.view_calender.CalendarViewerControllerImpl
import calendar.ui.component.CalendarGridPresenter
import calendar.ui.admin.HolidayEditorController
import calendar.ui.component.CalendarViewerController
import calendar.ui.public_.view_calender.CalendarViewController
import di.DIFactory

object UIFactory {
    fun createCalenderController(): CalendarViewController =
        CalendarViewControllerImpl(
            viewerController = _createViewerController(),
            presenter = _createPresenter(),
            useCase =  DIFactory.readAcademicCalender()
        )


    fun calenderEditorController(): HolidayEditorController =
        HolidayEditorControllerImpl(
            selectionController = _createSelectionController(),
            viewerController = _createViewerController(),
            presenter = _createPresenter(),
            readRawUseCase = DIFactory.readRawCalenderUseCase(),
            command = _createAddCommand(),
            readAcademicUseCase = DIFactory.readAcademicCalender()
        )
    fun entryController():CalenderEntryController=CalenderEntryControllerImpl(
        insertUseCase = DIFactory.insertUseCase(),
        updateUseCase = DIFactory.updateUseCase()
    )

    private fun _createViewerController(): CalendarViewerController =
        CalendarViewerControllerImpl()
    private fun _createPresenter(): CalendarGridPresenter = CalendarUIGridModelPresenterImpl()
    private fun _createAddCommand(): AddHolidayCommand = AddHolidayCommandImpl()
    private fun _createSelectionController(): HolidayDateSelector = HolidayDateSelectorImpl()
}