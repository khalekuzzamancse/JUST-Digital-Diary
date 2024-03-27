package database.local.schema

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

/**
 * Converts directly to a table or equivalent structure.
 * This should not be exposed as a public API. Instead, use separate classes for exposing data
 * and accepting input for loose coupling.
Additionally, this class is a subclass of [RealmObject]. Exposing it would require clients to depend on Realm,
leading to an undesirable architecture.

 */
class TeacherSchema : RealmObject {
    var id:Int=0 //used to sort the item so that appears same as the back-end
    @PrimaryKey
    var uid: String = ""
    var name: String = ""
    var email: String = ""
    var achievement: String = ""
    var additionalEmail: String? = null
    var phone: String = ""
    var profileImage: String = ""
    var roomNo: Int = 0
    var designations: RealmList<DesignationSchema> = realmListOf()
    var deptId: String = ""
    var departmentName: String = ""
    var shortname: String = ""

    override fun toString(): String {
        return "TeacherRealm(uid='$uid', name='$name', email='$email', achievement='$achievement', additional_email=$additionalEmail, phone='$phone', profile_image='$profileImage', room_no=$roomNo,  designations=$designations, dept_id='$deptId',  department_name='$departmentName', shortname='$shortname')"
    }

    fun toEntity() = TeacherEntityLocal(
        uid = uid,
        name = name,
        email = email,
        achievement = achievement,
        additionalEmail = additionalEmail,
        phone = phone,
        profileImage = profileImage,
        roomNo = roomNo,
        designations = designations.map { it.toEntity() },
        deptId = deptId,
        departmentName = departmentName,
        shortName = shortname,
        id = id
    )
}

open class DesignationSchema : RealmObject {
    var name: String = ""

    override fun toString(): String {
        return "DesignationRealm(name='$name')"
    }

    fun toEntity() = DesignationEntityLocal(name = name)
}

data class TeacherEntityLocal(
    val  id:Int,//used to sort the item so that appears same as the back-end
    val uid: String,
    val name: String,
    val email: String,
    val achievement: String,
    val additionalEmail: String?,
    val phone: String,
    val profileImage: String,
    val roomNo: Int,
    val designations: List<DesignationEntityLocal>,
    val deptId: String,
    val departmentName: String,
    val shortName: String
)

data class DesignationEntityLocal(
    val name: String
)
