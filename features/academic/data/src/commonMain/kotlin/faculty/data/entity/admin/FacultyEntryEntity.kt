package faculty.data.entity.admin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** According to database schema documentation
 * - Can be used for both create,read and update
 * - In case of create consumer will not decide the [facultyId] that is why it has default value
 *
 *   Insert Instruction:
 *   - Must override the [facultyId], because it is not decided by the client/consumer
 *   - Do not insert the [numberOfDept] , because it derived property that will be used at while reading the list of faculty
 *
 *  Read Instruction:
 *   - Fetch the number of departments and fill the [numberOfDept] field, for testing purposes allowed ignore it
 *
 */
@Serializable
internal data class FacultyEntryEntity(
    @SerialName("priority") val priority: Int,
    @SerialName("faculty_id") val facultyId: String = "not_decided yet",
    @SerialName("name") val name: String,
    @SerialName("number_of_dept") val numberOfDept: Int = 0,
)
