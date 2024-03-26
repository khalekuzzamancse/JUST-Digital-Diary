package core.database.realm.academic

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

/**
 * * This will direcly converted to Table or Equivalent ,if this structure is not matched
 * with backed the then response will be failed
 * * Do not edit it
 */
internal class FacultyEntityRealm : RealmObject {
    var id: Int = 0
    @PrimaryKey
    var faculty_id: String = "0"
    var name: String = "0"
    var department_count: Int = 0
    override fun toString(): String {
        return "FacultyEntity(id=$id, faculty_id='$faculty_id', name='$name', department_count=$department_count)"
    }

    fun toModel() = FacultyLocalModel(
        id = id, facultyId = faculty_id, name = name, deptCount = department_count
    )
}

data class FacultyLocalModel(
    val id: Int,
    val facultyId: String,
    val name: String,
    val deptCount: Int
)
