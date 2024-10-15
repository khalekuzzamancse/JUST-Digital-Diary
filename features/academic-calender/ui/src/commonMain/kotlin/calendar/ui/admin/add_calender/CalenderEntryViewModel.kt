package calendar.ui.admin.add_calender

import calendar.presentationlogic.ModelMapper
import calendar.presentationlogic.controller.admin.CalenderEntryController
import calendar.ui.admin.HolidayEditorController

class CalenderEntryViewModel(
    val editor: HolidayEditorController,
    val controller: CalenderEntryController
) {
    suspend fun insert() = controller.insert(
        ModelMapper.holidayModel(
            year = editor.currentYear.value!!,
            yearData = editor.getYearData()
        )
    )

    suspend fun update() = controller.update(
        ModelMapper.holidayModel(
            year = editor.currentYear.value!!,
            yearData = editor.getYearData()
        )
    )

}