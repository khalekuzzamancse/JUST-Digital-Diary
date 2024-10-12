@file:Suppress("functionName")

package academic.presentationlogic.factory.admin

import academic.presentationlogic.controller.admin.DepartmentEntryController
import academic.presentationlogic.mapper.ModelMapper
import academic.presentationlogic.model.admin.DepartmentEntryModel
import academic.presentationlogic.model.public_.FacultyModel
import faculty.domain.exception.CustomException
import faculty.domain.usecase.admin.AddDepartmentUseCase
import faculty.domain.usecase.public_.RetrieveFactualityUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class DeptEntryControllerImpl(
    private val retrieveFactualityUseCase: RetrieveFactualityUseCase,
    private val addDepartmentUseCase: AddDepartmentUseCase,
) : DepartmentEntryController {
    private val _networkIOInProgress = MutableStateFlow(false)
    private val _dept = MutableStateFlow(DepartmentEntryModel("", "", "", ""))
    private val _statusMessage = MutableStateFlow<String?>(null)


    //    private val _faculty = MutableStateFlow<List<FacultyModel>>(emptyList())
    private val _faculty = MutableStateFlow<List<FacultyModel>>(emptyList())
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

    init {
        CoroutineScope(Dispatchers.Default).launch {
            _retrieveFaculties()
        }
    }

    override suspend fun onAddRequest() {
        _onNetworkIOStart()
        addDepartmentUseCase
            .execute(with(ModelMapper) { _dept.value.toDomainModelOrThrow() })
            .fold(
                onSuccess = {
                    _updateErrorMessage("Added Successfully")
                },
                onFailure = { exception ->
                    when (exception) {
                        is CustomException -> {
                            _updateErrorMessage(exception.message)
                        }

                        else -> {
                            _updateErrorMessage("Failed to load faculties")
                        }

                    }
                }
            )
        _onNetworkIOStop()
    }

    private suspend fun _retrieveFaculties() {
        _onNetworkIOStart()
        retrieveFactualityUseCase
            .execute()
            .fold(
                onSuccess = { models ->
                    _faculty.update { models.map { with(ModelMapper) { it.toUiModel() } } }
                    //  _updateErrorMessage("Added Successfully")

                },
                onFailure = { exception ->
                    when (exception) {
                        is CustomException -> {
                            _updateErrorMessage(exception.message)
                        }

                        else -> {
                            _updateErrorMessage("Failed to load faculties")
                        }

                    }
                }
            )
        _onNetworkIOStop()
    }

    override suspend fun onUpdateRequest() {

    }

    private fun _onNetworkIOStart() = _networkIOInProgress.update { true }
    private fun _onNetworkIOStop() = _networkIOInProgress.update { false }
    private fun _updateErrorMessage(msg: String) {
        CoroutineScope(Dispatchers.Default).launch {
            _statusMessage.update { msg }
            //clear after 4 seconds
            delay(4_000)
            _statusMessage.update { null }
        }
    }
}