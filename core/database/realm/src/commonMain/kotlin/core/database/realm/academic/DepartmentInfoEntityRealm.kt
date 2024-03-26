package core.database.realm.academic

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

internal class DepartmentInfoEntityRealm : RealmObject {

    @PrimaryKey
    var dept_id: String = "0"
    var name: String = "0"
    var shortname: String = "0"
    var employeeCount: Int = 0
    var facultyId:String=""
    


    fun toModel() = DepartmentEntityLocalModel(
        deptId = dept_id,
        employeeCount = employeeCount,
        name = name, 
        shortname = shortname
    )

    override fun toString(): String {
        return "DepartmentInfoEntityRealm(dept_id='$dept_id', name='$name', shortname='$shortname', employeeCount=$employeeCount, facultyId='$facultyId')"
    }
}

data class DepartmentEntityLocalModel(
    val deptId: String,
    val employeeCount: Int,
    val name: String,
    val shortname: String
)
