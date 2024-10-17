package academic.ui.public_

import academic.presentationlogic.controller.public_.DepartmentController
import academic.presentationlogic.controller.public_.FacultyController
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import common.ui.SnackBarMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FacultyScreenViewModel internal constructor(
    internal val facultyController: FacultyController,
    internal val departmentController: DepartmentController
) : ViewModel() {

    private val _showDepartments = MutableStateFlow(false)
    val showDepartments = _showDepartments.asStateFlow()
    fun onDeptCloseRequest() {
        _showDepartments.update { false }
        //clear the faculty selection
        facultyController.onSelected(null)

    }

    val isLoading: Flow<Boolean> =
        combine(departmentController.isLoading, facultyController.isLoading)
        { isLogging, isRegistering ->
            isLogging || isRegistering
        }
    val screenMessage: Flow<SnackBarMessage?> =
        combine(departmentController.statusMessage, facultyController.statusMessage)
        { loginMsg, registerMsg ->
            loginMsg ?: registerMsg
        }

    init {
        viewModelScope.launch {
            facultyController.fetchFaculty()
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
                        departmentController.fetchDepartments(facultyId)
                        _showDepartments.update { true }
                    } catch (_: Exception) {

                    }

                }

            }

        }
    }


}
