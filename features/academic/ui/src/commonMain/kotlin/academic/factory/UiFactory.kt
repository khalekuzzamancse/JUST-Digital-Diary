@file:Suppress("FunctionName")

package academic.factory

import academic.factory.add_teacher.TeacherFormControllerImpl
import academic.ui.admin.TeacherFormController
import academic.factory.add_teacher.ValidatorImpl
import academic.ui.public_.department.DepartmentController
import faculty.di.DiContainer
import academic.ui.public_.FacultiesScreenViewModel
import academic.ui.public_.faculty.FacultyController
import academic.ui.public_.teachers.TeacherListViewModel
import academic.ui.public_.teachers.TeachersController

internal object UiFactory {
    fun createFacultyViewModel() = FacultiesScreenViewModel(
        facultyController = _createFacultyController(),
        departmentController = _createDepartmentsController()
    )
    internal fun createTeachersController(): TeachersController=TeachersControllerImpl(
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