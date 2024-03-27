package database.local.schema.administration

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

/**
 * Represents the schema for an admin sub-office in the Realm database.
 * This class should remain internal to avoid exposing the database implementation details.
 */
internal class AdminSubOfficeSchema : RealmObject {
    var id: Int = 0 // Used for sorting, ensuring consistency with the backend.
    @PrimaryKey
    var subOfficeId: String = "0"
    var name: String = "0"
    var officeMembersCount: Int = 0

    fun toEntity() = AdminSubOfficeEntityLocal(
        id = id,
        subOfficeId = subOfficeId,
        name = name,
        officeMembersCount = officeMembersCount
    )

    override fun toString(): String {
        return "AdminSubOfficeInfoSchema(id=$id, subOfficeId='$subOfficeId', name='$name', officeMembersCount=$officeMembersCount)"
    }
}


/**
 * Represents the local entity for admin sub-office information.
 * This class is used for data interactions within the application, abstracting the database layer.
 */
data class AdminSubOfficeEntityLocal(
    val id: Int, // Used for sorting, aligning with the backend representation.
    val subOfficeId: String,
    val name: String,
    val officeMembersCount: Int
)
