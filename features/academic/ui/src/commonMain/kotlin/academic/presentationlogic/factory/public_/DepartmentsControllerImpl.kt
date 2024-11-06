@file:Suppress("unused", "FunctionName")

package academic.presentationlogic.factory.public_

import academic.presentationlogic.controller.core.CoreController
import academic.presentationlogic.controller.public_.DepartmentController
import academic.presentationlogic.ModelMapper
import academic.presentationlogic.model.DepartmentReadModel
import faculty.domain.usecase.public_.ReadDepartmentsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * Though it allowed to use the delete controller here but right now not use here,
 * just focusing on single operation(Reading),if some external consumer want to delete
 * while showing the list by this controller then do not forget to call the [refresh] method
 */
internal class DepartmentsControllerImpl(
    private val userCase: ReadDepartmentsUseCase,
) : DepartmentController, CoreController() {

    private val _departments = MutableStateFlow<List<DepartmentReadModel>>(emptyList())
    private val _selected = MutableStateFlow<Int?>(null)
    /**Will use for refresh*/
    private var _facultyId:String? = null

    override val statusMessage = super._statusMessage.asStateFlow()
    override val isLoading = super._isLoading.asStateFlow()

    override val departments = _departments.asStateFlow()
    override val selected = _selected.asStateFlow()
    override fun onSelected(index: Int) {
        _selected.update { index }

    }

    override fun clearSelection() {
        _selected.update { null }
    }

    override suspend fun readDepartments(facultyId: String) {
        this._facultyId =facultyId
        super.startLoading()
        val result = userCase.execute(facultyId = facultyId)
        result.fold(
            onSuccess = { models ->
                _departments.update {
                    with(ModelMapper){
                        models.map { it.toModel() }
                    }
                }
            },
            onFailure = { exception -> exception.showStatusMsg() }
        )
        super.stopLoading()
    }

    override suspend fun refresh() {
        _facultyId?.let {facultyId->
            readDepartments(facultyId)
        }
    }


}