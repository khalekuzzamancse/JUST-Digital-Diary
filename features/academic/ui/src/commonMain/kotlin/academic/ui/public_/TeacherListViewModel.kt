package academic.ui.public_

import academic.controller_presenter.controller.TeachersController
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * - Just holder of the controller so that it service on configuration change
 */
class TeacherListViewModel internal  constructor(
   internal  val controller: TeachersController
):ViewModel(){
   val isLoading = controller.isFetching
   val screenMessage=controller.errorMessage
}




