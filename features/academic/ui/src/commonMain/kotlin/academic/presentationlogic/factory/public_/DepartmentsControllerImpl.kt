@file:Suppress("unused", "FunctionName")

package academic.presentationlogic.factory.public_

import academic.presentationlogic.controller.core.CoreControllerImpl
import academic.presentationlogic.controller.public_.DepartmentController
import academic.presentationlogic.mapper.ReadModelMapper
import academic.presentationlogic.model.DepartmentReadModel
import common.ui.SnackBarMessage
import core.customexception.CustomException
import faculty.domain.usecase.public_.ReadDepartmentsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class DepartmentsControllerImpl(
    private val userCase: ReadDepartmentsUseCase,
) : DepartmentController, CoreControllerImpl() {

    private val _departments = MutableStateFlow<List<DepartmentReadModel>>(emptyList())
    private val _selected = MutableStateFlow<Int?>(null)

    override val statusMessage = super._statusMessage.asStateFlow()
    override val isLoading = super._isLoading.asStateFlow()

    override val departments = _departments.asStateFlow()
    override val selected = _selected.asStateFlow()
    override fun onSelected(index: Int) {
        _selected.update { index }

    }

    override suspend fun fetchDepartments(facultyId: String) {
        super.startLoading()
        val result = userCase.execute(facultyId = facultyId)
        result.fold(
            onSuccess = { models ->
                _departments.update {
                    with(ReadModelMapper){
                        models.map { it.toModel() }
                    }
                }
            },
            onFailure = { exception -> exception.updateStatusMessage() }
        )
        super.stopLoading()
    }

}