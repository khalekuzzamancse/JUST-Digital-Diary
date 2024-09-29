package academic.ui.public_

import academic.controller_presenter.controller.TeachersController


/**
 * - Just holder of the controller so that it service on configuration change
 */
class TeacherListViewModel internal  constructor(
   internal  val controller: TeachersController
){
   val isLoading = controller.isFetching
   val screenMessage=controller.errorMessage
}




