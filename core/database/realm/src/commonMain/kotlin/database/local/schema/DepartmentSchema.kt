package database.local.schema

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

/**
 * Converts directly to a table or equivalent structure.
 * This should not be exposed as a public API. Instead, use separate classes for exposing data
 * and accepting input for loose coupling.
Additionally, this class is a subclass of [RealmObject]. Exposing it would require clients to depend on Realm,
leading to an undesirable architecture.

 */
internal class DepartmentSchema : RealmObject {
    var id:Int=0 //used to sort the item so that appears same as the back-end
    @PrimaryKey
    var deptId: String = "0"
    var name: String = "0"
    var shortname: String = "0"
    var employeeCount: Int = 0
    var facultyId: String = ""


    fun toEntity() = DepartmentEntityLocal(
        id=id,//used to sort the item so that appears same as the back-end
        deptId = deptId,
        employeeCount = employeeCount,
        name = name,
        shortname = shortname
    )

    override fun toString(): String {
        return "DepartmentInfoEntityRealm(dept_id='$deptId', name='$name', shortname='$shortname', employeeCount=$employeeCount, facultyId='$facultyId')"
    }
}

/**
 * Used for exposing data to and accepting input from clients.
 * This is analogous to JSON responses or requests in backend services.
 */
data class DepartmentEntityLocal(
    val id:Int ,//used to sort the item so that appears same as the back-end
    val deptId: String,
    val employeeCount: Int,
    val name: String,
    val shortname: String
)
