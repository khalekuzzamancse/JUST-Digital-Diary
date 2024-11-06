package administration.controller_presenter.controller

import administration.controller_presenter.model.SubOfficeModel
import kotlinx.coroutines.flow.StateFlow

internal interface SubOfficeController {
    val isFetching: StateFlow<Boolean>
    val errorMessage: StateFlow<String?>
    val sobOffices: StateFlow<List<SubOfficeModel>>
    val selected: StateFlow<Int?>
    fun clearSelection()
    fun onSelected(index: Int?)
    suspend fun fetch(officeId:String)

}