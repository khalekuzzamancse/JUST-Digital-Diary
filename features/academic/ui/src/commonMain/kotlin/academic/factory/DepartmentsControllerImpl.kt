@file:Suppress("unused", "FunctionName")

package academic.factory

import academic.model.DepartmentModel
import academic.presenter.Presenter
import academic.ui.public_.department.DepartmentController
import faculty.domain.usecase.RetrieveDepartmentsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class DepartmentsControllerImpl(
    private val userCase: RetrieveDepartmentsUseCase,
) : DepartmentController {
    private val _isLoading = MutableStateFlow(false)
    override val isLoading = _isLoading.asStateFlow()
    private val _departements = MutableStateFlow<List<DepartmentModel>>(emptyList())
    override val departments = _departements.asStateFlow()
    private val _selected = MutableStateFlow<Int?>(null)
    override val selected = _selected.asStateFlow()
    override fun onSelected(index: Int) {
        _selected.update { index }

    }
    init {
        CoroutineScope(Dispatchers.Default).launch {
            fetchFaculty()
        }
    }

    private suspend fun fetchFaculty() {
        _startLoading()
        val result = userCase.execute("","")
        result.fold(
            onSuccess = { models ->
                _departements.update {
                    models
                        .map { Presenter.toUiFacultyModel(it) }
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