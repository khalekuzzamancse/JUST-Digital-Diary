package administration.controller_presenter.factory

import administration.controller_presenter.controller.EmployeeListController
import administration.controller_presenter.controller.OfficeController
import administration.controller_presenter.controller.SubOfficeController
import administration.di.DiContainer
import administration.controller_presenter.factory.add_officers.OfficerFormControllerImpl
import administration.controller_presenter.factory.add_officers.ValidatorImpl
import administration.ui.admin.add_officers.OfficerFormController

internal object UiFactory {

    fun createOfficersAddFormController(): OfficerFormController =
        OfficerFormControllerImpl(validator = ValidatorImpl())

    fun createOfficerController(token: String?): OfficeController = OfficeControllerImpl(
        userCase = DiContainer.createGetOfficesUseCase(token)
    )
    fun createSubOfficeController(token: String?): SubOfficeController = SubOfficeControllerImpl(
        useCase = DiContainer.createGetSubOfficesUseCase(token)
    )
    fun createEmployeeController(token: String?): EmployeeListController = EmployeeListControllerImpl(
        useCase = DiContainer.createGetAdminOfficersUseCase(token)
    )
}