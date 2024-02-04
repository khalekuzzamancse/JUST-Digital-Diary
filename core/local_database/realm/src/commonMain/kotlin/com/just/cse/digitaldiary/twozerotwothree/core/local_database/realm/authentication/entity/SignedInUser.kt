package com.just.cse.digitaldiary.twozerotwothree.core.local_database.realm.authentication.entity

import io.realm.kotlin.types.RealmObject

class SignedInUser : RealmObject {
    var username: String? = null
    var password: String? = null
    var authToken: String? = null
    override fun toString(): String {
        return "User(username:$username, password:$password ,token:$authToken)"
    }


}