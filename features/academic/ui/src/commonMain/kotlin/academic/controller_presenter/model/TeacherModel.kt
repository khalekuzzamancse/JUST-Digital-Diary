package academic.controller_presenter.model

/**
 * - Used for admin ....
 *
 */
internal data class TeacherModel(
    val name: String,
    val email: String,
    val additionalEmail: String,
    val achievements: String,
    val phone: String,
    val designations: String,
    val dept: Dept, // Use dropdown
    val roomNo: Int,
    val profileImageLink: String = "https://static.just.edu.bd/images/public/teachers/1603270274986_900.jpeg",
)

data class Dept(
    val name: String,
    val shortname: String
) {
    override fun toString(): String {
        return "$name ( $shortname)"
    }
}
