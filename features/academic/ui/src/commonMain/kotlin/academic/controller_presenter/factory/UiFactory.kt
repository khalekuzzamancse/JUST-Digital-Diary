@file:Suppress("FunctionName")

package academic.controller_presenter.factory

import academic.controller_presenter.factory.add_teacher.TeacherFormControllerImpl
import academic.controller_presenter.controller.TeacherFormController
import academic.controller_presenter.factory.add_teacher.ValidatorImpl
import academic.controller_presenter.controller.DepartmentController
import faculty.di.DiContainer
import academic.ui.public_.FacultiesScreenViewModel
import academic.controller_presenter.controller.FacultyController
import academic.controller_presenter.controller.TeachersController

internal object UiFactory {
    fun createFacultyViewModel() = FacultiesScreenViewModel(
        facultyController = _createFacultyController(),
        departmentController = _createDepartmentsController()
    )
    internal fun createTeachersController(): TeachersController = TeachersControllerImpl(
        useCase = DiContainer.retrieveTeacherListUseCase()
    )

    fun createTeacherAddForm(): TeacherFormController =
        TeacherFormControllerImpl(validator = ValidatorImpl())

    private fun _createFacultyController(): FacultyController = FacultyControllerImpl(
        userCase = DiContainer.retrieveFacultyListUseCase(),
    )

    private fun _createDepartmentsController(): DepartmentController =
        DepartmentsControllerImpl(
            userCase = DiContainer.retrieveDepartListUseCase()
        )


}