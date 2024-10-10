@file:Suppress("FunctionName")

package academic.presentationlogic.factory

import academic.presentationlogic.controller.admin.FacultyEntryController
import academic.presentationlogic.factory.admin.TeacherFormControllerImpl
import academic.presentationlogic.controller.admin.TeacherFormController
import academic.presentationlogic.factory.admin.TeacherEntryValidatorImpl
import academic.presentationlogic.controller.public_.DepartmentController
import academic.presentationlogic.controller.admin.DepartmentEntryController
import faculty.di.DiContainer
import academic.presentationlogic.controller.public_.FacultyController
import academic.presentationlogic.controller.public_.TeachersController
import academic.presentationlogic.factory.admin.DeptEntryControllerImpl
import academic.presentationlogic.factory.admin.FacultyEntryControllerImpl
import academic.presentationlogic.factory.public_.DepartmentsControllerImpl
import academic.presentationlogic.factory.public_.FacultyControllerImpl
import academic.presentationlogic.factory.public_.TeachersControllerImpl

internal object UiFactory {


    internal fun createTeachersController(token:String?): TeachersController =
        TeachersControllerImpl(
            useCase = DiContainer.retrieveTeacherListUseCase(token),

    )

    fun createTeacherAddForm(): TeacherFormController =
        TeacherFormControllerImpl(validator = TeacherEntryValidatorImpl())

    fun createFacultyController(token:String?): FacultyController = FacultyControllerImpl(
        userCase = DiContainer.retrieveFacultyListUseCase(token),
    )

    fun createDepartmentsController(token:String?): DepartmentController =
        DepartmentsControllerImpl(
            userCase = DiContainer.retrieveDepartListUseCase(token),
        )
    fun createAddFacultyController(): FacultyEntryController = FacultyEntryControllerImpl()
    fun createDepartEntryController(): DepartmentEntryController = DeptEntryControllerImpl()


}