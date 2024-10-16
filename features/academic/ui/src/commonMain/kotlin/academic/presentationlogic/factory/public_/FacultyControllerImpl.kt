@file:Suppress("unused", "FunctionName")

package academic.presentationlogic.factory.public_

import academic.presentationlogic.controller.core.CoreControllerImpl
import academic.presentationlogic.controller.public_.FacultyController
import academic.presentationlogic.mapper.PublicModelMapper
import academic.presentationlogic.model.public_.FacultyModel
import core.customexception.CustomException
import faculty.domain.usecase.public_.ReadAllFactualityUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class FacultyControllerImpl(
    private val userCase: ReadAllFactualityUseCase,
) : FacultyController, CoreControllerImpl() {
    private val _faculties = MutableStateFlow<List<FacultyModel>>(emptyList())
    private val _selected = MutableStateFlow<Int?>(null)

    override val statusMessage = super._statusMessage.asStateFlow()
    override val isLoading = super._isLoading.asStateFlow()

    override val faculties = _faculties.asStateFlow()
    override val selected = _selected.asStateFlow()
    override fun onSelected(index: Int?) {
        _selected.update { index }

    }

    override suspend fun fetchFaculty() {
        super.startLoading()
        val result = userCase.execute()
        result.fold(
            onSuccess = { models ->
                _faculties.update {
                    models
                        .map { PublicModelMapper.toUiFacultyModel(it) }
                }
            },
            onFailure = { exception ->
                when (exception) {
                    is CustomException -> super.updateErrorMessage(exception.message)
                    else -> super.updateErrorMessage("Something went wrong")
                }
            }
        )
        super.stopLoading()
    }

}