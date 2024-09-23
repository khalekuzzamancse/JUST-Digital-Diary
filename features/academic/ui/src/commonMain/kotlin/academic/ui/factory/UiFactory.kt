package academic.ui.factory

import academic.ui.factory.add_teacher.TeacherFormControllerImpl
import academic.ui.admin.add_teacher.TeacherFormController
import academic.ui.factory.add_teacher.ValidatorImpl
import faculty.di.DiContainer
import academic.ui.other.faculty.FacultiesScreenViewModel
import academic.ui.non_admin.teachers.TeacherListViewModel

object UiFactory {
    fun createFacultyViewModel() = FacultiesScreenViewModel(
        facultyUserCase = DiContainer.retrieveFacultyListUseCase(),
        retrieveDeptListUseCase = DiContainer.retrieveDepartListUseCase()
    )

    fun createTeachersViewModel() = TeacherListViewModel(
        useCase = DiContainer.retrieveTeacherListUseCase()
    )

    fun createTeacherAddForm(): TeacherFormController =
        TeacherFormControllerImpl(validator = ValidatorImpl())

}