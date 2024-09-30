package administration.ui.public_

import administration.controller_presenter.controller.EmployeeListController


/**
 * - Just holder of the controller so that it service on configuration change
 */
class EmployeeListViewModel internal  constructor(
   internal  val controller: EmployeeListController
){
   val isLoading = controller.isFetching
   val screenMessage=controller.errorMessage
}




