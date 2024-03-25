package queryservice.data.data_sources.remote.entity


data class EmployeeEntity(
    val primaryKey:String,
    val name: String,
    val email: String,
    val additionalEmail: String,
    val profileImageLink: String="https://static.just.edu.bd/images/public/teachers/1603270274986_900.jpeg",
    val achievements: String,
    val phone: String,
    val designations: String,
    val roomNo: String,
)