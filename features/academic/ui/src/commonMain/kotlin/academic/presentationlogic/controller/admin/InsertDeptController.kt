package academic.presentationlogic.controller.admin

internal interface InsertDeptController:DeptEntryController {
    suspend fun insert()
}
