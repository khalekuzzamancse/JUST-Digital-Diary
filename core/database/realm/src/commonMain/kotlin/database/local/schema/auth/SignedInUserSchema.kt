package database.local.schema.auth

import io.realm.kotlin.types.RealmObject
/**
 * Converts directly to a table or equivalent structure.
 * This should not be exposed as a public API. Instead, use separate classes for exposing data
 * and accepting input for loose coupling.
Additionally, this class is a subclass of [RealmObject]. Exposing it would require clients to depend on Realm,
leading to an undesirable architecture.

 */
internal class SignedInUserSchema : RealmObject {
    var username: String? = null
    var password: String? = null
    var authToken: String? = null
    override fun toString(): String {
        return "User(username:$username, password:$password ,token:$authToken)"
    }


}

data class SignedInUserEntityLocal(
    val username: String,
    val password: String
)