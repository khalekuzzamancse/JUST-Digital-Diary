package academic.presentationlogic.model

/**
 * @property facultyId useful for admin to load the faculty info when updating a selected department from the list
 */
internal class DepartmentReadModel(
    val name: String,
    val shortname: String,
    val id: String,
    val membersCount:String,
    val facultyId:String=""
)