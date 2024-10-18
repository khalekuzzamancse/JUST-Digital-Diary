@file:Suppress("unused","spellCheckingInspection")

package academic.presentationlogic.controller.admin

import academic.presentationlogic.controller.core.ICoreController

/**
 * This controller may be sued by some other controller such as TeacherList may have a button on each
 * teacher to delete a teacher, once the user click the delete button then the teacher controller should
 * delete the request to this controller,once the delete is successful, it is make sense to refresh
 * the teacher list,so the the consumer controller should refresh the list once this function hit the return
 * statement.
 * Since user will click the teacher so it is guaranteed that the teacher id is valid ,so in 99% case the
 * operation is successful(insh'Allah) , so need not  to explicitly tell the consumer controller that delete was
 * successful or not
 *
 *
 * - Usage Guide:
 *
 * This is UI lightweight operation compare to the insertion and update operations because insertion and update
 * need separate screen or UI, because then need to load more information,
 * Since this operation do not need so much UI elements such as single button is enough so instead of implementing
 * separate screen , it make sense to use it with `Read` operation such with faculty list, teacher list and department list
 * because once we delete it is make sense to to `read` again(`refresh`),so these two operations works well together
 * so you can use this controller with the controller that are doing `Reading` operations.
 * Note: This just a recommendation if you find a better way to do that then you are welcome...
 *
 *
 * - TODO:Add the authentication/validation check the user has permission or not to delete
 */
interface DeleteController : ICoreController {
    suspend fun deleteFaculty(id: String)
    suspend fun deleteDepartment(id: String)
    suspend fun deleteTeacher(id: String)
}