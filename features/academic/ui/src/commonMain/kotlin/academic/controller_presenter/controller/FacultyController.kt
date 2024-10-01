package academic.controller_presenter.controller

import academic.controller_presenter.model.FacultyModel
import kotlinx.coroutines.flow.StateFlow

internal interface FacultyController {
    val isFetching: StateFlow<Boolean>
    val faculties: StateFlow<List<FacultyModel>>
    val errorMessage: StateFlow<String?>
    val selected: StateFlow<Int?>
  suspend fun  fetchFaculty()
    fun onSelected(index: Int?)
}