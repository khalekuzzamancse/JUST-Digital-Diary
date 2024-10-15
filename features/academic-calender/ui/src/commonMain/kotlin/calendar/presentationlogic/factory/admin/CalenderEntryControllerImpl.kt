package calendar.presentationlogic.factory.admin

import calendar.presentationlogic.controller.admin.CalenderEntryController
import calendar.presentationlogic.controller.core.CoreControllerImpl
import feature.academiccalender.domain.exception.CustomException
import feature.academiccalender.domain.model.AcademicCalender2
import feature.academiccalender.domain.usecase.InsertUseCase
import feature.academiccalender.domain.usecase.UpdateUseCase

class CalenderEntryControllerImpl(
    private val updateUseCase: UpdateUseCase,
    private val insertUseCase: InsertUseCase
) : CalenderEntryController, CoreControllerImpl() {

    override val isLoading = super._isLoading
    override val statusMessage = super._statusMessage
    override suspend fun insert(calender: AcademicCalender2) {
        super.startLoading()
        insertUseCase.execute(calender)
            .fold(
                onSuccess = {
                    super.updateErrorMessage("Inserted Successfully")
                },
                onFailure = { exception ->
                    when (exception) {
                        is CustomException -> super.updateErrorMessage(exception.message)
                        else ->
                            super.updateErrorMessage("$exception Failed to load faculties")
                    }
                }
            )
        super.stopLoading()
    }

    override suspend fun update(calender: AcademicCalender2) {
        super.startLoading()
        updateUseCase.execute(calender)
            .fold(
                onSuccess = {
                    super.updateErrorMessage("Updated Successfully")
                },
                onFailure = { exception ->
                    when (exception) {
                        is CustomException -> super.updateErrorMessage(exception.message)
                        else ->
                            super.updateErrorMessage("Failed to update")
                    }
                }
            )
        super.stopLoading()
    }

}