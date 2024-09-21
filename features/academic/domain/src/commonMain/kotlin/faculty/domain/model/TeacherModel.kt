package faculty.domain.model

/**
 * @param id for ordering or sorting
 */
data class TeacherModel(
    val id:Int=0,
    val name: String,
    val email: String,
    val additionalEmail: String,
    val profileImageLink: String="https://static.just.edu.bd/images/public/teachers/1603270274986_900.jpeg",
    val achievements: String,
    val phone: String,
    val designations: String,
    val deptName: String,
    val deptSortName: String,
    val roomNo: Int,
)