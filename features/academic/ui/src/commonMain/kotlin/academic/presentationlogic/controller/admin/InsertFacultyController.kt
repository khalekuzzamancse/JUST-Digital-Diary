package academic.presentationlogic.controller.admin

internal interface InsertFacultyController:FacultyEntryController {
    suspend fun insert()

}
