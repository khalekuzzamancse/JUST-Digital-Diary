package com.just.cse.digitaldiary.twozerotwothree.core.local_database.realm

import com.just.cse.digitaldiary.twozerotwothree.core.local_database.realm.authentication.entity.SignedInUser
import com.just.cse.digitaldiary.twozerotwothree.core.local_database.realm.authentication.responose_model.SignedInUserResponseModel
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.asFlow
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object RealmAuthentication {
    private val configuration = RealmConfiguration.create(
        schema = setOf(Person::class, Dog::class, SignedInUser::class)
    )
    private val realm = Realm.open(configuration)


    suspend fun saveSignInInfo(
        requestModel: SignedInUserResponseModel
    ): SignedInUserResponseModel? {
        val entityAlreadyExistsAndUpdated =
            updateUser(requestModel.username, requestModel.password) != null
        return if (entityAlreadyExistsAndUpdated)
            requestModel
        else {
            createEntity(requestModel)
        }

    }

    fun isSignedIn(): Boolean {
        return getSingedInUserInfo() != null
    }

    private suspend fun createEntity(requestModel: SignedInUserResponseModel): SignedInUserResponseModel? {
        val user = SignedInUser().apply {
            this.username = requestModel.username
            this.password = requestModel.password
        }
        val job: Deferred<SignedInUser> = CoroutineScope(Dispatchers.Default).async {
            realm.write {
                val signedInUser = copyToRealm(user)
                signedInUser
            }
        }
        val userInfo = job.await()
        val userName = userInfo.username
        val pass = userInfo.password
        return if (userName != null && pass != null) {
            SignedInUserResponseModel(
                username = userName,
                password = pass
            )

        } else
            null

    }

    fun updateToken(token: String?): Boolean {
        if (!isSignedIn())
            return false
        val response = realm.query<SignedInUser>()
            .first()
            .find()
            ?.also { user ->
                realm.writeBlocking {
                    findLatest(user)?.let {
                        it.authToken = token
                    }
                }
            }
        return response != null
    }

    private fun updateUser(
        userName: String?,
        password: String?,
        token: String? = null,
    ): SignedInUser? {
        val response = realm.query<SignedInUser>()
            .first()
            .find()
            ?.also { user ->
                realm.writeBlocking {
                    findLatest(user)?.let {
                        it.username = userName
                        it.password = password
                        it.authToken = token
                    }
                }
            }
        return response
    }

    fun signOut(): Boolean {
        val response = updateUser(null, null, null)
        response?.let {
            if (it.username == null || it.password == null)
                return true
        }
        return false
    }

    fun getSingedInUserInfo(): SignedInUserResponseModel? {
        val response: RealmResults<SignedInUser> = realm.query<SignedInUser>().find()
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

    fun observeSignIn(): Flow<Boolean> {
        val isSignIn = realm.query<SignedInUser>().find().first()
            .asFlow().map {
                val user = it.obj
                if (user == null) false
                else {
                    user.username != null && user.password != null
                }
            }
        return isSignIn
    }

    fun getToken(): String? {
        val response: RealmResults<SignedInUser> = realm.query<SignedInUser>().find()
        if (response.isNotEmpty()) {
            val signedInUser: SignedInUser = response.first()
            val username = signedInUser.username
            val password = signedInUser.password
            if (username != null && password != null) {
                return signedInUser.authToken
            }

        }
        return null
    }

}
/*
Save info  on sign in ,until sign out
 */