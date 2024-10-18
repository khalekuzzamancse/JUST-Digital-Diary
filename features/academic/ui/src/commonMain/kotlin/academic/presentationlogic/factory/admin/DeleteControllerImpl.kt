@file:Suppress("unused")
package academic.presentationlogic.factory.admin

import academic.presentationlogic.controller.admin.DeleteController
import academic.presentationlogic.controller.core.CoreController
import faculty.domain.usecase.admin.DeleteDepartmentUseCase
import faculty.domain.usecase.admin.DeleteFacultyUseCase
import faculty.domain.usecase.admin.DeleteTeacherUseCase
import kotlinx.coroutines.flow.asStateFlow

internal class DeleteControllerImpl(
    private val facultyDeleteCase:DeleteFacultyUseCase,
    private val deptDeleteCase:DeleteDepartmentUseCase,
    private val teacherDeleteCase:DeleteTeacherUseCase

): DeleteController, CoreController() {

    override val statusMessage = super._statusMessage.asStateFlow()
    override val isLoading = super._isLoading.asStateFlow()

    override suspend fun deleteFaculty(id: String) {
        super.startLoading()
        facultyDeleteCase
            .execute(id)
            .showStatusMsg(operation = "Delete")
        super.stopLoading()

    }

    override suspend fun deleteDepartment(id: String) {
        super.startLoading()
        deptDeleteCase
            .execute(id)
            .showStatusMsg(operation = "Delete")
        super.stopLoading()
    }

    override suspend fun deleteTeacher(id: String) {
        super.startLoading()
        teacherDeleteCase
            .execute(id)
            .showStatusMsg(operation = "Delete")
        super.stopLoading()
    }

}