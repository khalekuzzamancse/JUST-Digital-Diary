package academic.ui.public_

import academic.presentationlogic.controller.admin.DeleteController
import academic.presentationlogic.controller.public_.TeachersController
import androidx.lifecycle.ViewModel
import common.ui.SnackBarMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine


/**
 * - Just holder of the controller so that it service on configuration change
 */
class TeacherListViewModel internal constructor(
    internal val teachersController: TeachersController,
    private val deleteController: DeleteController
) : ViewModel() {
    private val scope = CoroutineScope(Dispatchers.Default)

    val isLoading: Flow<Boolean> =
        combine(
            teachersController.isLoading,
            deleteController.isLoading
        )
        { readingTeacher, deleting ->
            readingTeacher || deleting
        }
    val screenMessage: Flow<SnackBarMessage?> =
        combine(
            teachersController.statusMessage,
            deleteController.statusMessage,
        )
        { readingMeg, deletingMsg ->
            readingMeg ?:deletingMsg
        }


    suspend fun delete(id:String){
        deleteController.deleteTeacher(id)
        teachersController.refresh()
    }

}




