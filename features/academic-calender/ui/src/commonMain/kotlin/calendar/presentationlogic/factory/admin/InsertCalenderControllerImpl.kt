package calendar.presentationlogic.factory.admin

import calendar.presentationlogic.controller.admin.InsertCalenderController
import calendar.presentationlogic.controller.core.CoreControllerImpl
import domain.exception.CustomException
import domain.model.AcademicCalender2
import domain.usecase.InsertCalendarUseCase

class InsertCalenderControllerImpl(
    private val useCase: InsertCalendarUseCase
) : InsertCalenderController, CoreControllerImpl() {

    override val isLoading = super._isLoading
    override val statusMessage = super._statusMessage
    override suspend fun insert(calender: AcademicCalender2) {
        super.startLoading()
        useCase.execute(calender)
            .fold(
                onSuccess = {
                    super.updateErrorMessage("Added Successfully")
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

}