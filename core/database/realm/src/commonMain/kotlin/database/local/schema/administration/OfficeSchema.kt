package database.local.schema.administration

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

/**
 * Converts directly to a table or equivalent structure.
 * This should not be exposed as a public API. Instead, use separate classes for exposing data
 * and accepting input for loose coupling.
Additionally, this class is a subclass of [RealmObject]. Exposing it would require clients to depend on Realm,
leading to an undesirable architecture.

 */

internal class OfficeSchema : RealmObject {
    var id: Int = 0 // Used to sort the item so that it appears the same as the back-end
    @PrimaryKey
    var officeId: String = "0"
    var name: String = "0"
    var subOfficesCount: Int = 0

    fun toEntity() = OfficeEntityLocal(
        id = id,
        officeId = officeId,
        name = name,
        subOfficesCount = subOfficesCount
    )


    override fun toString(): String {
        return "AdminOfficeInfoSchema(id=$id, officeId='$officeId', name='$name', subOfficesCount=$subOfficesCount)"
    }
}

data class OfficeEntityLocal(
    val id: Int, // Used to sort the item so that it appears the same as the back-end
    val officeId: String,
    val name: String,
    val subOfficesCount: Int
)
