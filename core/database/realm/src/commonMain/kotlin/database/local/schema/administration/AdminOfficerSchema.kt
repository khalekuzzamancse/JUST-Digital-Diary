package database.local.schema.administration

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

/**
 * Represents the schema for an admin officer in the Realm database.
 * This internal class should not be exposed outside the database layer to maintain abstraction.
 */
internal class AdminOfficerSchema : RealmObject {
    @PrimaryKey
    var uid: String = ""
    var officeId:String=""
    var name: String = ""
    var email: String = ""
    var additionalEmail: String? = null
    var profileImage: String? = null
    var achievement: String = ""
    var phone: String? = null
    var designations: String = ""
    var roomNo: String = ""

    fun toEntity() = AdminOfficerEntityLocal(
        uid = uid,
        name = name,
        email = email,
        additionalEmail = additionalEmail,
        profileImage = profileImage,
        achievement = achievement,
        phone = phone,
        designations = designations,
        roomNo = roomNo,
        officeId = officeId
    )

    override fun toString(): String {
        return "AdminOfficerSchema(uid='$uid', officeId='$officeId', name='$name', email='$email', additionalEmail=$additionalEmail, profileImage=$profileImage, achievement='$achievement', phone=$phone, designations='$designations', roomNo='$roomNo')"
    }


}


/**
 * Represents the local entity for admin officer information.
 * This class is intended for use within the application, facilitating data handling and interactions.
 */
data class AdminOfficerEntityLocal(
    val officeId:String,
    val uid: String,
    val name: String,
    val email: String,
    val additionalEmail: String?,
    val profileImage: String?,
    val achievement: String,
    val phone: String?,
    val designations: String,
    val roomNo: String
)
