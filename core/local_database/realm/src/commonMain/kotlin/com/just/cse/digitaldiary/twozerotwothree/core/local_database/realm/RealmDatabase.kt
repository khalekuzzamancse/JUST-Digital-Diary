package com.just.cse.digitaldiary.twozerotwothree.core.local_database.realm

import com.just.cse.digitaldiary.twozerotwothree.core.local_database.realm.authentication.entity.SignedInUser
import com.just.cse.digitaldiary.twozerotwothree.core.local_database.realm.authentication.responose_model.SignedInUserResponseModel
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

object RealmDatabase {
    private val configuration = RealmConfiguration.create(
        schema = setOf(Person::class, Dog::class, SignedInUser::class)
    )
    private val realm = Realm.open(configuration)
    fun create() {
        val person = Person().apply {
            name = "Carlo"
            dog = Dog().apply { name = "Fido"; age = 16 }
        }
        CoroutineScope(Dispatchers.Default).async {
            realm.write {
                val managedPerson = copyToRealm(person)
                println("RealmDB:create() $managedPerson")
            }
        }
    }

    fun saveSignInInfo(
        username: String, password: String
    ) {
        val user = SignedInUser().apply {
            this.username = username
            this.password = password
        }
        CoroutineScope(Dispatchers.Default).async {
            realm.write {
                val signedInUser = copyToRealm(user)
                println("RealmDatabase:saveSignInInfo() $signedInUser")
            }
        }
    }

    fun getSingedInUserInfo(): SignedInUserResponseModel? {
      //  saveSignInInfo("khalek02","test@123")
        //
        val response: RealmResults<SignedInUser> = realm.query<SignedInUser>().find()
        println("RealmDatabase:getSingedInUserInfo():: $response")
        if (response.isNotEmpty()) {
            val signedInUser: SignedInUser = response.first()
            val username = signedInUser.username
            val password = signedInUser.password
            if (username != null && password != null) {
                return SignedInUserResponseModel(
                    username = username,
                    password = password
                )
            }

        }
        return null


    }

    fun getAll() {
        val all = realm.query<Person>().find()
        // if (all.isNotEmpty())
        println("RealmDB:getAll() ${all}")

    }
}