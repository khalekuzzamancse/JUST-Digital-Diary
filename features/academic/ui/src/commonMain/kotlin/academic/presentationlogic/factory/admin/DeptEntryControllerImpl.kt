@file:Suppress("functionName")

package academic.presentationlogic.factory.admin

import academic.presentationlogic.controller.admin.DepartmentEntryController
import academic.presentationlogic.model.admin.DepartmentEntryModel
import academic.presentationlogic.model.public_.FacultyModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

internal class DeptEntryControllerImpl : DepartmentEntryController {
    private val _networkIOInProgress = MutableStateFlow(false)
    private val _dept = MutableStateFlow(DepartmentEntryModel("", "", "", ""))
    private val _statusMessage = MutableStateFlow<String?>(null)
//    private val _faculty = MutableStateFlow<List<FacultyModel>>(emptyList())
private val _faculty = MutableStateFlow(_dummyList())
    private val _selectedFaculty = MutableStateFlow<Int?>(null)


    override val networkIOInProgress = _networkIOInProgress.asStateFlow()
    override val dept = _dept.asStateFlow()
    override val faculties = _faculty.asStateFlow()
    override val selectedFacultyIndex = _selectedFaculty.asStateFlow()
    override val statusMessage = _statusMessage.asStateFlow()


    override val validator = object : DepartmentEntryController.Validator {

        private val _fieldsFilled = MutableStateFlow(false)
        private val _errors = MutableStateFlow(emptyList<String>())
        override val areMandatoryFieldFilled = _fieldsFilled.asStateFlow()
        override val errors = _errors.asStateFlow()
        override fun observeFieldChanges(state: StateFlow<DepartmentEntryModel>) {
            combine(state) {
                it.first()
            }.onEach { model ->
                val name = model.name
                val id = model.priority
                val shortName = model.shortName
                //Right now Need not  validation ,just check all mandatory field are filled or not
                _fieldsFilled.update {
                    id.isNotBlank() && name.isNotBlank() && shortName.isNotBlank()
                }
            }.launchIn(CoroutineScope(Dispatchers.Default))

        }

    }

    init {
        validator.observeFieldChanges(dept)
    }


    override fun onNameChanged(value: String) = _dept.update { it.copy(name = value) }
    override fun onShortNameChanged(value: String) = _dept.update { it.copy(shortName = value) }
    override fun onPriorityChanged(value: String) = _dept.update { it.copy(priority = value) }
    override fun onFacultySelected(index: Int) {
        _selectedFaculty.update { index }
        try {//Since accessing index, taking double safety
            _dept.update { it.copy(facultyId = faculties.value[index].id) }
        } catch (_: Exception) {

        }

    }

    override suspend fun onAddRequest() {
        _onNetworkIOStart()

        _onNetworkIOStop()
    }

    override suspend fun onUpdateRequest() {
        _onNetworkIOStart()

        _onNetworkIOStop()
    }

    private fun _onNetworkIOStart() = _networkIOInProgress.update { true }
    private fun _onNetworkIOStop() = _networkIOInProgress.update { false }
    private fun _dummyList() = listOf(
        FacultyModel(
            name = "Faculty of Engineering & Technology",
            id = "1",
            numberOfDepartment = ""
        ),
        FacultyModel(
            name = "Faculty of Applied Science & Technology",
            id = "2",
            numberOfDepartment = ""
        ),
        FacultyModel(
            name = "Faculty of Biological Science & Technology",
            id = "3",
            numberOfDepartment = ""
        ),
        FacultyModel(name = "Faculty of Health Science", id = "4", numberOfDepartment = ""),
        FacultyModel(name = "Faculty of Business Studies", id = "5", numberOfDepartment = ""),
        FacultyModel(name = "Faculty of Social Science & Arts", id = "6", numberOfDepartment = "")
    )
}