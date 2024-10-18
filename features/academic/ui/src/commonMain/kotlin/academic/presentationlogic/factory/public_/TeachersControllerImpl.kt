@file:Suppress("unused", "FunctionName")

package academic.presentationlogic.factory.public_

import academic.presentationlogic.controller.core.CoreController
import academic.presentationlogic.controller.public_.TeachersController
import academic.presentationlogic.ModelMapper
import academic.presentationlogic.model.TeacherReadModel
import faculty.domain.usecase.public_.ReadTeachersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
/**
 * Though it allowed to use the delete controller here but right now not use here,
 * just focusing on single operation(Reading),if some external consumer want to delete
 * while showing the list by this controller then do not forget to call the [refresh] method
 */
internal class TeachersControllerImpl(
    private val useCase: ReadTeachersUseCase,
) : TeachersController, CoreController() {

    private val _teachers = MutableStateFlow<List<TeacherReadModel>>(emptyList())
    /**Will use for refresh*/
    private var _deptId:String? = null

    override val isLoading = super._isLoading.asStateFlow()
    override val statusMessage = super._statusMessage.asStateFlow()

    override val teachers = _teachers.asStateFlow()

    override suspend fun read(deptId: String) {
        this._deptId = deptId
        super.startLoading()
        val result = useCase.execute(deptId)
        result.fold(
            onSuccess = { models ->
                _teachers.update {
                    with(ModelMapper) {
                        models.map { it.toModel() }
                    }
                }
            },
            onFailure = { exception -> exception.showStatusMsg() }
        )
        super.stopLoading()
    }

    override suspend fun refresh() {
       _deptId?.let {deptId ->
           read(deptId)
       }

    }
}