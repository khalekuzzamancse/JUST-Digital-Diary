package calendar.presentationlogic.factory.admin

import calendar.presentationlogic.controller.admin.CalenderEntryController
import calendar.presentationlogic.controller.core.CoreController
import feature.academiccalender.domain.model.AcademicCalender2
import feature.academiccalender.domain.usecase.InsertUseCase
import feature.academiccalender.domain.usecase.UpdateUseCase

internal class CalenderEntryControllerImpl (
    private val updateUseCase: UpdateUseCase,
    private val insertUseCase: InsertUseCase
) : CalenderEntryController, CoreController() {

    override val isLoading = super._isLoading
    override val statusMessage = super._statusMessage
    override suspend fun insert(calender: AcademicCalender2) {
        super.startLoading()
        insertUseCase.execute(calender)

            .showStatusMsg(operation = "Insert")

        super.stopLoading()
    }

    override suspend fun update(calender: AcademicCalender2) {
        super.startLoading()

        updateUseCase.execute(calender)
            .showStatusMsg(operation = "Update")

        super.stopLoading()

    }

}