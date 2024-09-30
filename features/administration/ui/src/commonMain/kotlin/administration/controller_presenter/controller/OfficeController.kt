package administration.controller_presenter.controller

import administration.controller_presenter.model.OfficeModel
import kotlinx.coroutines.flow.StateFlow

internal interface OfficeController {
    val isFetching: StateFlow<Boolean>
    val offices: StateFlow<List<OfficeModel>>
    val errorMessage: StateFlow<String?>
    val selected: StateFlow<Int?>
    suspend fun fetch()
    fun onSelected(index: Int?)
}