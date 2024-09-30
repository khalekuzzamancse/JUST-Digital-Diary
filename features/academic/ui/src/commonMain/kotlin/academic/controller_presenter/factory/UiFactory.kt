@file:Suppress("FunctionName")

package academic.controller_presenter.factory

import academic.controller_presenter.factory.add_teacher.TeacherFormControllerImpl
import academic.controller_presenter.controller.TeacherFormController
import academic.controller_presenter.factory.add_teacher.ValidatorImpl
import academic.controller_presenter.controller.DepartmentController
import faculty.di.DiContainer
import academic.controller_presenter.controller.FacultyController
import academic.controller_presenter.controller.TeachersController

internal object UiFactory {


    internal fun createTeachersController(token:String?): TeachersController =
        TeachersControllerImpl(
            useCase = DiContainer.retrieveTeacherListUseCase(),
            token=token

    )

    fun createTeacherAddForm(): TeacherFormController =
        TeacherFormControllerImpl(validator = ValidatorImpl())

    fun createFacultyController(token:String?): FacultyController = FacultyControllerImpl(
        userCase = DiContainer.retrieveFacultyListUseCase(),
        token=token
    )

    fun createDepartmentsController(token:String?): DepartmentController =
        DepartmentsControllerImpl(
            userCase = DiContainer.retrieveDepartListUseCase(),
            token=token
        )


}