package administration.ui.public_

import administration.controller_presenter.controller.EmployeeListController
import androidx.lifecycle.ViewModel


/**
 * - Just holder of the controller so that it service on configuration change
 */
class EmployeeListViewModel internal  constructor(
   internal  val controller: EmployeeListController
):ViewModel(){
   val isLoading = controller.isFetching
   val screenMessage=controller.errorMessage
}




