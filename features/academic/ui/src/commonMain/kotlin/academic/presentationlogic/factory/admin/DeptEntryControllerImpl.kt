@file:Suppress("functionName","propertyName")

package academic.presentationlogic.factory.admin

import academic.presentationlogic.controller.admin.DeptEntryController
import academic.presentationlogic.controller.core.CoreControllerImpl
import academic.presentationlogic.mapper.ModelMapper
import academic.presentationlogic.model.admin.DepartmentEntryModel
import academic.presentationlogic.model.public_.FacultyModel
import faculty.domain.exception.CustomException
import faculty.domain.usecase.public_.RetrieveFactualityUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal open  class DeptEntryControllerImpl(
    private val readUseCase: RetrieveFactualityUseCase,
    override val validator  : DeptEntryController.Validator
) : DeptEntryController, CoreControllerImpl() {

    protected val _dept = MutableStateFlow(DepartmentEntryModel("", "", "", ""))

    //    private val _faculty = MutableStateFlow<List<FacultyModel>>(emptyList())
    private val _faculty = MutableStateFlow<List<FacultyModel>>(emptyList())
    private val _selectedFaculty = MutableStateFlow<Int?>(null)


    override val isLoading = _isLoading.asStateFlow()
    override val dept = _dept.asStateFlow()
    override val faculties = _faculty.asStateFlow()
    override val selectedFacultyIndex = _selectedFaculty.asStateFlow()
    override val statusMessage = _statusMessage.asStateFlow()


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
            retrieveFaculties()
        }
    }


    protected suspend fun retrieveFaculties() {
        super.startLoading()
        readUseCase
            .execute()
            .fold(
                onSuccess = { models ->
                    _faculty.update { models.map { with(ModelMapper) { it.toUiModel() } } }
                    //  _updateErrorMessage("Added Successfully")

                },
                onFailure = { exception ->
                    when (exception) {
                        is CustomException -> {
                            super.updateErrorMessage(exception.message)
                        }

                        else -> {
                            super.updateErrorMessage("Failed to load faculties")
                        }

                    }
                }
            )
        super.stopLoading()
    }


}