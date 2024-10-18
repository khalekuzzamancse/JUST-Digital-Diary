@file:Suppress("unused", "FunctionName")

package academic.presentationlogic.factory.public_

import academic.presentationlogic.controller.core.CoreController
import academic.presentationlogic.controller.public_.FacultyController
import academic.presentationlogic.ModelMapper
import academic.presentationlogic.model.FacultyReadModel
import faculty.domain.usecase.public_.ReadAllFactualityUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
/**
 * Though it allowed to use the delete controller here but right now not use here,
 * just focusing on single operation(Reading),if some external consumer want to delete
 * while showing the list by this controller then do not forget to call the [refresh] method
 */
internal class FacultyControllerImpl(
    private val userCase: ReadAllFactualityUseCase,
) : FacultyController, CoreController() {
    private val _faculties = MutableStateFlow<List<FacultyReadModel>>(emptyList())
    private val _selected = MutableStateFlow<Int?>(null)

    override val statusMessage = super._statusMessage.asStateFlow()
    override val isLoading = super._isLoading.asStateFlow()

    override val faculties = _faculties.asStateFlow()
    override val selected = _selected.asStateFlow()
    override fun onSelected(index: Int?) {
        _selected.update { index }

    }

    override suspend fun readFaculties() {
        super.startLoading()
        val result = userCase.execute()
        result.fold(
            onSuccess = { models ->
                _faculties.update {
                    with(ModelMapper) {
                        models.map { it.toModel() }
                    }
                }
            },
            onFailure = { exception -> exception.showStatusMsg() }
        )
        super.stopLoading()
    }

    override suspend fun refresh()=readFaculties()

}