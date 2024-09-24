@file:Suppress("FunctionName")
package calendar.ui.factory

import calendar.ui.factory.add_calender.AddHolidayCommand
import calendar.ui.factory.add_calender.AddHolidayCommandImpl
import calendar.ui.factory.add_calender.CalendarViewControllerImpl
import calendar.ui.factory.add_calender.HolidayDateSelector
import calendar.ui.factory.add_calender.HolidayEditorControllerImpl
import calendar.ui.factory.add_calender.HolidayDateSelectorImpl
import calendar.ui.factory.common.CalendarUIGridModelPresenterImpl
import calendar.ui.factory.view_calender.CalendarViewerControllerImpl
import calendar.ui.presenter.CalendarGridPresenter
import calendar.ui.ui.admin.HolidayEditorController
import calendar.ui.ui.common.CalendarViewerController
import calendar.ui.ui.public_.CalendarViewController
import di.DIFactory

object UIFactory {
    fun createCalenderController(): CalendarViewController =
        CalendarViewControllerImpl(
            viewerController = _createViewerController(),
            presenter = _createPresenter(),
            useCase =  DIFactory.createAcademicRetrieveCalenderUseCase()
        )


    fun createCalenderEditorController(): HolidayEditorController =
        HolidayEditorControllerImpl(
            selectionController = _createSelectionController(),
            viewerController = _createViewerController(),
            presenter = _createPresenter(),
            useCase = DIFactory.createRawRetrieveCalenderUseCase(),
            command = _createAddCommand()
        )

    private fun _createViewerController(): CalendarViewerController =
        CalendarViewerControllerImpl()
    private fun _createPresenter(): CalendarGridPresenter = CalendarUIGridModelPresenterImpl()
    private fun _createAddCommand(): AddHolidayCommand = AddHolidayCommandImpl()
    private fun _createSelectionController(): HolidayDateSelector = HolidayDateSelectorImpl()
}