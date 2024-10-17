package schedule.presentationlogic.factory.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import schedule.domain.usecase.ReadAllDeptUseCase
import schedule.presentationlogic.controller.core.AcademicInfoController
import schedule.presentationlogic.controller.core.CoreController
import schedule.presentationlogic.model.DepartmentModel

internal class AcademicInfoControllerImpl(
    override val validator: AcademicInfoController.Validator,
    private val allDeptUseCase: ReadAllDeptUseCase,
) : AcademicInfoController,CoreController() {
    private val _selectedDeptIndex = MutableStateFlow<Int?>(null)
    private val _dept = MutableStateFlow<List<DepartmentModel>>(emptyList())
    private val _year = MutableStateFlow("")
    private val _semester = MutableStateFlow("")
    private val _session = MutableStateFlow("")


    override val department = _dept.asStateFlow()
    override val selectedDeptIndex = _selectedDeptIndex.asStateFlow()
    override val year: StateFlow<String> = _year.asStateFlow()
    override val semester: StateFlow<String> = _semester.asStateFlow()
    override val session: StateFlow<String> = _session.asStateFlow()

    init {
        CoroutineScope(Dispatchers.Default).launch {
            readAllDept()
        }
    }

    override val isLoading = super._isLoading
    override val statusMessage = super._statusMessage

    override fun onDeptSelected(index: Int) = _selectedDeptIndex.update { index }
    override fun onYearChanged(value: String) {
        _year.update { value.filter { it.isDigit() } }//only number is allowed
    }

    override fun onSessionChanged(value: String) {
        _session.update { value.filter { it.isDigit() || it == '-' } } //only number and `'` are allowed
    }

    override fun onSemesterChanged(value: String) {
        _semester.update { value.filter { it.isDigit() } }//only number is allowed
    }
    private suspend fun readAllDept() {
        super.startLoading()
        allDeptUseCase
            .execute()
            .fold(
                onSuccess = { models ->
                    _dept.update {
                        models.map {model->
                            DepartmentModel(
                                deptId = model.deptId,
                                name = model.name,
                                shortname = model.shortname
                            )
                        }

                    }

                },
                onFailure = { ex ->ex.showStatusMsg(optionalMsg = "Unable to load departments") }
            )
        super.stopLoading()
    }
}