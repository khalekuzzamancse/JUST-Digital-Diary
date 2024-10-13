package academic.presentationlogic.controller.admin

internal interface InsertFacultyController:FacultyAdminBaseController {
    suspend fun onAddRequest()

}
