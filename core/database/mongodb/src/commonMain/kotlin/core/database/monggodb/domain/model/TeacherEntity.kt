package core.database.monggodb.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
/**
 * - Can be used for both insert,read and update
 * - In case of insert consumer will not decide the [id] that is why it has default value
 *
 *   Insert Instruction:
 *   - Must override the [id], because it is not decided by the client/consumer
 *   - Take the `deptId` via the insert method, as a result this can be easily reused for insert,update,read
 *
 *  Read Instruction:
 *   - Nothing to say
 *
 */
@Serializable
internal data class TeacherEntity(
    @SerialName("id") val id: String="not decided",
    @SerialName("dept_id") val deptId: String="not decided",
    @SerialName("priority") val priority: Int,
    @SerialName("name") val name: String,
    @SerialName("email") val email: String,
    @SerialName("additional_email") val additionalEmail: String?,
    @SerialName("achievements") val achievements: String,
    @SerialName("phone") val phone: String,
    @SerialName("designations") val designations: String,
    @SerialName("room_no") val roomNo: String?
)
