@file:Suppress("unused", "FunctionName")

package academic.factory

import academic.model.FacultyModel
import academic.presenter.Presenter
import academic.ui.public_.faculty.FacultyController
import faculty.domain.usecase.RetrieveFactualityUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class FacultyControllerImpl(
    private val userCase: RetrieveFactualityUseCase,
) : FacultyController {
    private val _isLoading = MutableStateFlow(false)
    override val isLoading = _isLoading.asStateFlow()
    private val _faculties = MutableStateFlow<List<FacultyModel>>(emptyList())
    override val faculties = _faculties.asStateFlow()
    private val _selected = MutableStateFlow<Int?>(null)
    override val selected = _selected.asStateFlow()
    override fun onSelected(index: Int?) {
        _selected.update { index }

    }
    init {
        CoroutineScope(Dispatchers.Default).launch {
            fetchFaculty()
        }
    }

    private suspend fun fetchFaculty() {
        _startLoading()
        val result = userCase.execute(token = "")
        result.fold(
            onSuccess = { models ->
                _faculties.update {
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