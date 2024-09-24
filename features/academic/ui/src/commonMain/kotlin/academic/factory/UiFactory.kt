@file:Suppress("FunctionName")

package academic.factory

import academic.factory.add_teacher.TeacherFormControllerImpl
import academic.ui.admin.TeacherFormController
import academic.factory.add_teacher.ValidatorImpl
import academic.factory.faculty.FacultyControllerImpl
import academic.ui.public_.department.DepartmentController
import faculty.di.DiContainer
import academic.ui.public_.FacultiesScreenViewModel
import academic.ui.public_.faculty.FacultyController
import academic.ui.public_.teachers.TeacherListViewModel

object UiFactory {
    fun createFacultyViewModel() = FacultiesScreenViewModel(
        facultyController = _createFacultyController(),
        departmentController = _createDepartmentsController()
    )

    fun createTeachersViewModel() = TeacherListViewModel(
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