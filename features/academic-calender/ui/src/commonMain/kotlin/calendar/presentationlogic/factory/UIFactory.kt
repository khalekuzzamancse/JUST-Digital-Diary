@file:Suppress("FunctionName")
package calendar.presentationlogic.factory

import calendar.presentationlogic.controller.admin.CalenderEntryController
import calendar.presentationlogic.factory.admin.CalenderEntryControllerImpl
import calendar.ui.admin.AddHolidayCommand
import calendar.ui.admin.AddHolidayCommandImpl
import calendar.ui.admin.HolidayDateSelector
import calendar.ui.admin.HolidayEditorControllerImpl
import calendar.ui.admin.HolidayDateSelectorImpl
import calendar.ui.component.GridManagerImpl
import calendar.ui.component.CalendarGridManager
import calendar.ui.admin.HolidayEditorController
import calendar.ui.component.CalendarViewerController
import calendar.presentationlogic.controller.public_.CalendarViewController
import calendar.ui.component.CalendarViewerControllerImpl
import acdemiccalender.di.DiContainer

 object UIFactory {
    fun createCalenderController(): CalendarViewController =
        CalendarViewControllerImpl(
            viewerController = _createViewerController(),
            presenter = _createPresenter(),
            useCase =  DiContainer.readAcademicCalender()
        )


    internal fun calenderEditorController(): HolidayEditorController =
        HolidayEditorControllerImpl(
            selectionController = _createSelectionController(),
            viewerController = _createViewerController(),
            presenter = _createPresenter(),
            readRawUseCase = DiContainer.readRawCalenderUseCase(),
            command = _createAddCommand(),
            readAcademicUseCase = DiContainer.readAcademicCalender()
        )
    fun entryController(): CalenderEntryController =CalenderEntryControllerImpl(
        insertUseCase = DiContainer.insertUseCase(),
        updateUseCase = DiContainer.updateUseCase()
    )

    private fun _createViewerController(): CalendarViewerController =
        CalendarViewerControllerImpl()
    private fun _createPresenter(): CalendarGridManager = GridManagerImpl()
    private fun _createAddCommand(): AddHolidayCommand = AddHolidayCommandImpl()
    private fun _createSelectionController(): HolidayDateSelector = HolidayDateSelectorImpl()
}