package faculty.data.entity.admin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
/**
 * According to the database schema
 *
 * - Can be used for both insert,read and update
 * - In case of insert consumer will not decide the [deptId], [numOfEmployee] that is why it has default value
 *
 *   Insert Instruction:
 *   - Must override the [deptId], because it is not decided by the client/consumer
 *   - Do not insert the [numOfEmployee] , because it derived property that will be used at while reading the list
 *   - Take the `facultyId` via the insert method, as a result this can be easily reused for insert,update,read
 *
 *  Read Instruction:
 *   - Fetch the number of  [numOfEmployee] field and fill it, for testing purposes allowed ignore it
 *
 */

@Serializable
internal data class DepartmentEntryEntity(
    @SerialName("priority") val priority: Int,
    @SerialName("name") val name: String,
    @SerialName("shortname") val shortname: String,
    @SerialName("dept_id") val deptId: String="not_decided",
    @SerialName("number_of_employee") val numOfEmployee: Int=0,

    )
