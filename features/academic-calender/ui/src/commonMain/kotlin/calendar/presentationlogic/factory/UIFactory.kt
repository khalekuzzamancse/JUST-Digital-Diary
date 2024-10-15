@file:Suppress("FunctionName")
package calendar.presentationlogic.factory

import calendar.presentationlogic.factory.add_calender.AddHolidayCommand
import calendar.presentationlogic.factory.add_calender.AddHolidayCommandImpl
import calendar.presentationlogic.factory.add_calender.CalendarViewControllerImpl
import calendar.presentationlogic.factory.add_calender.HolidayDateSelector
import calendar.presentationlogic.factory.add_calender.HolidayEditorControllerImpl
import calendar.presentationlogic.factory.add_calender.HolidayDateSelectorImpl
import calendar.presentationlogic.factory.common.CalendarUIGridModelPresenterImpl
import calendar.presentationlogic.factory.view_calender.CalendarViewerControllerImpl
import calendar.presentationlogic.presenter.CalendarGridPresenter
import calendar.ui.admin.HolidayEditorController
import calendar.ui.common.CalendarViewerController
import calendar.ui.public_.CalendarViewController
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