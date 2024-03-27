package database.local.schema.academic

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey


/**
 * Converts directly to a table or equivalent structure.
 * This should not be exposed as a public API. Instead, use separate classes for exposing data
 * and accepting input for loose coupling.
Additionally, this class is a subclass of [RealmObject]. Exposing it would require clients to depend on Realm,
leading to an undesirable architecture.

 */
internal class FacultySchema : RealmObject {

    var id: Int = 0 //  used to sort the item so that appears same as the back-end
    @PrimaryKey
    var facultyId: String = "0"
    var name: String = "0"
    var departmentCount: Int = 0
    override fun toString(): String {
        return "FacultyEntity(id=$id, faculty_id='$facultyId', name='$name', department_count=$departmentCount)"
    }

    fun toEntity() = FacultyEntityLocal(
        id = id, facultyId = facultyId, name = name, deptCount = departmentCount
    )
}

/**
 * Used for exposing data to and accepting input from clients.
 * This is analogous to JSON responses or requests in backend services.
 */
data class FacultyEntityLocal(
    val id: Int,
    val facultyId: String,
    val name: String,
    val deptCount: Int
)
