package core.database.realm.academic

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey


class TeacherEntityRealm : RealmObject {
    @PrimaryKey
    var uid: String = ""
    var name: String = ""
    var username: String = ""
    var email: String = ""
    var achievement: String = ""
    var additional_email: String? = null
    var phone: String = ""
    var profile_image: String = ""
    var room_no: Int = 0
    var type: Int = 0
    var designations: RealmList<DesignationRealm> = realmListOf()
    var dept_id: String = ""
    var present: Int = 0
    var department_name: String = ""
    var shortname: String = ""

    override fun toString(): String {
        return "TeacherRealm(uid='$uid', name='$name', username='$username', email='$email', achievement='$achievement', additional_email=$additional_email, phone='$phone', profile_image='$profile_image', room_no=$room_no, type=$type, designations=$designations, dept_id='$dept_id', present=$present, department_name='$department_name', shortname='$shortname')"
    }

    fun toModel() = TeacherLocalModel(
        uid = uid,
        name = name,
        email = email,
        achievement = achievement,
        additionalEmail = additional_email,
        phone = phone,
        profileImage = profile_image,
        roomNo = room_no,
        designations = designations.map { it.toModel() },
        deptId = dept_id,
        departmentName = department_name,
        shortName = shortname
    )
}

open class DesignationRealm : RealmObject {
    var name: String = ""

    override fun toString(): String {
        return "DesignationRealm(name='$name')"
    }

    fun toModel() = DesignationLocalModel(name = name)
}

data class TeacherLocalModel(
    val uid: String,
    val name: String,
    val email: String,
    val achievement: String,
    val additionalEmail: String?,
    val phone: String,
    val profileImage: String,
    val roomNo: Int,
    val designations: List<DesignationLocalModel>,
    val deptId: String,
    val departmentName: String,
    val shortName: String
)

data class DesignationLocalModel(
    val name: String
)
