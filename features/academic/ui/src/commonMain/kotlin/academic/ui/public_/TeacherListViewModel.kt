package academic.ui.public_

import academic.presentationlogic.controller.public_.TeachersController
import androidx.lifecycle.ViewModel


/**
 * - Just holder of the controller so that it service on configuration change
 */
class TeacherListViewModel internal  constructor(
   internal  val controller: TeachersController
):ViewModel(){
   val isLoading = controller.isLoading
   val screenMessage=controller.statusMessage
}




