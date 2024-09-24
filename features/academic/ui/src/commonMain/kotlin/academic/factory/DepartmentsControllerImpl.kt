@file:Suppress("unused", "FunctionName")

package academic.factory

import academic.model.DepartmentModel
import academic.mapper.ModelMapper
import academic.ui.public_.department.DepartmentController
import faculty.domain.usecase.RetrieveDepartmentsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class DepartmentsControllerImpl(
    private val userCase: RetrieveDepartmentsUseCase,
) : DepartmentController {
    private val _isLoading = MutableStateFlow(false)
    override val isFetching = _isLoading.asStateFlow()
    private val _departments = MutableStateFlow<List<DepartmentModel>>(emptyList())
    override val departments = _departments.asStateFlow()
    private val _selected = MutableStateFlow<Int?>(null)
    override val selected = _selected.asStateFlow()
    override fun onSelected(index: Int) {
        _selected.update { index }

    }

    override suspend fun fetchDepartments(facultyId: String) {
        _startLoading()
        val result = userCase.execute("", facultyId)
        result.fold(
            onSuccess = { models ->
                _departments.update {
                    models
                        .map { ModelMapper.toUiFacultyModel(it) }
                }
            },
            onFailure = {

            }
        )
        _startLoading()
    }

    private fun _startLoading() = _isLoading.update { true }
    private fun _stopLoading() = _isLoading.update { false }
}