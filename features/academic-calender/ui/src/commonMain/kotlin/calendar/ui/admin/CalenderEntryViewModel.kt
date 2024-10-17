package calendar.ui.admin

import calendar.presentationlogic.ModelMapper
import calendar.presentationlogic.controller.admin.CalenderEntryController

internal class CalenderEntryViewModel(
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