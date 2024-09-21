package academic.ui

import faculty.di.DiContainer
import faculty.ui.faculty.FacultiesScreenViewModel
import faculty.ui.teachers.TeacherListViewModel

object UiFactory {
    fun createFacultyViewModel()= FacultiesScreenViewModel(
        facultyUserCase = DiContainer.retrieveFacultyListUseCase(),
       retrieveDeptListUseCase = DiContainer.retrieveDepartListUseCase()
    )
    fun createTeachersViewModel()= TeacherListViewModel(
        useCase = DiContainer.retrieveTeacherListUseCase()
    )
}