package academic.ui.public_

import academic.presentationlogic.controller.admin.DeleteController
import academic.presentationlogic.controller.public_.DepartmentController
import academic.presentationlogic.controller.public_.FacultyController
import academic.ui.AcademicModuleEvent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import common.ui.SnackBarMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class FacultyScreenViewModel(
    val facultyController: FacultyController,
    val departmentController: DepartmentController,
    private val deleteController: DeleteController
) : ViewModel() {

    private val scope = CoroutineScope(Dispatchers.Default)
    private val _showDepartments = MutableStateFlow(false)
    val showDepartments = _showDepartments.asStateFlow()
    fun onDeptCloseRequest() {
        _showDepartments.update { false }
        //clear the faculty selection
        facultyController.onSelected(null)

    }
    init {
        CoroutineScope(Dispatchers.Default).launch {
            _showDepartments.collect{show->
                if (!show)
                    departmentController.clearSelection()

            }
        }
    }

    val isLoading: Flow<Boolean> =
        combine(
            departmentController.isLoading,
            facultyController.isLoading,
            deleteController.isLoading
        )
        { readingDept, readingFaculty, deleting ->
            readingDept || readingFaculty || deleting
        }
    val screenMessage: Flow<SnackBarMessage?> =
        combine(
            departmentController.statusMessage,
            facultyController.statusMessage,
            deleteController.statusMessage,
        )
        { loginMsg, registerMsg, deleterMsg ->
            loginMsg ?: registerMsg ?: deleterMsg
        }
    suspend fun deleteFaculty(id:String){
        deleteController.deleteFaculty(id)
        facultyController.refresh()
    }
    suspend fun deleteDepartment(id:String){
        deleteController.deleteDepartment(id)
        departmentController.refresh()
    }

    fun onAdminEvent(event: AcademicModuleEvent.AdminEvent) {
        scope.launch {
            when (event) {
                is AcademicModuleEvent.DeleteFacultyRequest -> {
                    deleteController.deleteFaculty(event.id)
                }

                is AcademicModuleEvent.DeleteDeptRequest -> {
                    deleteController.deleteDepartment(event.id)
                }

            }
        }


    }

    init {
        viewModelScope.launch {
            facultyController.readFaculties()
        }
    }

    init {

        CoroutineScope(Dispatchers.Default).launch {
            facultyController.selected.collect { facultyIndex ->
                //TODO: a faculty is selected,find the faculty id and load it dept
                departmentController.departments
                //Right now just pretending that department is loaded
                if (facultyIndex != null) {
                    try {
                        val facultyId = facultyController.faculties.value[facultyIndex].id
                        departmentController.readDepartments(facultyId)
                        _showDepartments.update { true }
                    } catch (_: Exception) {

                    }

                }

            }

        }
    }


}
