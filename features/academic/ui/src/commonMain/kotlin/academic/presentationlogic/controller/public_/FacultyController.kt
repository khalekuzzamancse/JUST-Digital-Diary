package academic.presentationlogic.controller.public_

import academic.presentationlogic.model.public_.FacultyModel
import kotlinx.coroutines.flow.StateFlow

internal interface FacultyController {
    val isFetching: StateFlow<Boolean>
    val faculties: StateFlow<List<FacultyModel>>
    val errorMessage: StateFlow<String?>
    val selected: StateFlow<Int?>
    suspend fun fetchFaculty()
    fun onSelected(index: Int?)

}