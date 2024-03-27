package database.local.schema.administration

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

/**
 * Represents the schema for an admin sub-office in the Realm database.
 * This class should remain internal to avoid exposing the database implementation details.
 */
internal class SubOfficeSchema : RealmObject {
    var serialNo: Int = 0 // Used for sorting, ensuring consistency with the backend.
    var officeId: String = "0"
    @PrimaryKey
    var name: String = "0"
    var officeMembersCount: Int = 0

    fun toEntity() = SubOfficeEntityLocal(
        serialNo = serialNo,
        officeId = officeId,
        name = name,
        officeMembersCount = officeMembersCount
    )

    override fun toString(): String {
        return "AdminSubOfficeInfoSchema(id=$serialNo, subOfficeId='$officeId', name='$name', officeMembersCount=$officeMembersCount)"
    }
}


/**
 * Represents the local entity for admin sub-office information.
 * This class is used for data interactions within the application, abstracting the database layer.
 */
data class SubOfficeEntityLocal(
    val serialNo: Int, // Used for sorting, aligning with the backend representation.
    val officeId: String,
    val name: String,
    val officeMembersCount: Int
)

