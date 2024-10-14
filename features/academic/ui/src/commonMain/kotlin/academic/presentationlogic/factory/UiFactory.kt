@file:Suppress("FunctionName")

package academic.presentationlogic.factory

import academic.presentationlogic.controller.admin.DeptEntryController
import academic.presentationlogic.controller.admin.FacultyEntryController
import academic.presentationlogic.controller.admin.InsertFacultyController
import academic.presentationlogic.factory.admin.TeacherEntryControllerImpl
import academic.presentationlogic.controller.admin.TeacherEntryController
import academic.presentationlogic.factory.admin.TeacherEntryValidatorImpl
import academic.presentationlogic.controller.public_.DepartmentController
import academic.presentationlogic.controller.admin.InsertDeptController
import academic.presentationlogic.controller.admin.UpdateDeptController
import academic.presentationlogic.controller.admin.UpdateFacultyController
import faculty.di.DiContainer
import academic.presentationlogic.controller.public_.FacultyController
import academic.presentationlogic.controller.public_.TeachersController
import academic.presentationlogic.factory.admin.DepartmentEntryValidatorImpl
import academic.presentationlogic.factory.admin.InsertDeptControllerImpl
import academic.presentationlogic.factory.admin.FacultyEntryValidatorImpl
import academic.presentationlogic.factory.admin.InsertFacultyControllerImpl
import academic.presentationlogic.factory.admin.UpdateDeptControllerImpl
import academic.presentationlogic.factory.admin.UpdateFacultyControllerImpl
import academic.presentationlogic.factory.public_.DepartmentsControllerImpl
import academic.presentationlogic.factory.public_.FacultyControllerImpl
import academic.presentationlogic.factory.public_.TeachersControllerImpl

internal object UiFactory {


    internal fun createTeachersController(token: String?): TeachersController =
        TeachersControllerImpl(
            useCase = DiContainer.retrieveTeacherListUseCase(token),

            )

    fun createTeacherAddForm(): TeacherEntryController =
        TeacherEntryControllerImpl(
            validator = TeacherEntryValidatorImpl(),
            allDeptUseCase = DiContainer.getAllDeptUseCase(),
            addTeacherUser = DiContainer.addTeacherUseCase()
        )

    fun createFacultyController(token: String?): FacultyController = FacultyControllerImpl(
        userCase = DiContainer.retrieveFacultyListUseCase(token),
    )

    fun createDepartmentsController(token: String?): DepartmentController =
        DepartmentsControllerImpl(
            userCase = DiContainer.retrieveDepartListUseCase(token),
        )

    fun insertFacultyController(): InsertFacultyController = InsertFacultyControllerImpl(
        useCase = DiContainer.addFacultyUseCase(),
        validator = _facultyEntryValidator()
    )

    fun updateFacultyController(facultyId: String): UpdateFacultyController =
        UpdateFacultyControllerImpl(
            facultyId = facultyId,
            readUseCase = DiContainer.readFacultyUseCase(),
            writeUseCase = DiContainer.updateFacultyUseCase(),
            validator = _facultyEntryValidator()
        )

    fun insertDeptController(): InsertDeptController = InsertDeptControllerImpl(
        readUseCase = DiContainer.retrieveFacultyListUseCase(null),
        writeUseCase = DiContainer.insertDeptUseCase(),
        validator = _deptEntryValidator()
    )
    fun updateDeptController(deptId:String): UpdateDeptController = UpdateDeptControllerImpl(
        deptId=deptId,
        readFacultyUseCase =  DiContainer.retrieveFacultyListUseCase(null),
        readDeptUseCase = DiContainer.readDeptUseCase(),
        writeUseCase =DiContainer.updateDeptUseCase() ,
        validator = _deptEntryValidator()
    )

    private fun _deptEntryValidator(): DeptEntryController.Validator =
        DepartmentEntryValidatorImpl()
    private fun _facultyEntryValidator(): FacultyEntryController.Validator =FacultyEntryValidatorImpl()


}