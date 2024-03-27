package database.local.api

import database.local.schema.SignedInUserEntityLocal
import database.local.schema.SignedInUserSchema
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

object AuthAPIs {
    private val configuration = RealmConfiguration.create(
        schema = setOf(SignedInUserSchema::class)
    )
    private val _signInFlow = MutableStateFlow(false)
     val signInFlow = _signInFlow.asStateFlow()

    val realm = Realm.open(configuration)


    suspend fun saveSignInInfo(
        requestModel: SignedInUserEntityLocal
    ): SignedInUserEntityLocal? {
        val entityAlreadyExistsAndUpdated =
            updateUser(requestModel.username, requestModel.password) != null
        val res = if (entityAlreadyExistsAndUpdated)
            requestModel
        else {
            createEntity(requestModel)
        }
        val isSaved=res!=null
        if (isSaved)
        _signInFlow.update { true }
        else
            _signInFlow.update { false }
        return res

    }

    fun isSignedIn(): Boolean {
        return getSingedInUserInfo() != null
    }

    private suspend fun createEntity(requestModel: SignedInUserEntityLocal): SignedInUserEntityLocal? {
        val user = SignedInUserSchema().apply {
            this.username = requestModel.username
            this.password = requestModel.password
        }
        val job: Deferred<SignedInUserSchema> = CoroutineScope(Dispatchers.Default).async {
            realm.write {
                val signedInUser = copyToRealm(user)
                signedInUser
            }
        }
        val userInfo = job.await()
        val userName = userInfo.username
        val pass = userInfo.password
        return if (userName != null && pass != null) {
            SignedInUserEntityLocal(
                username = userName,
                password = pass
            )

        } else
            null

    }

    fun updateToken(token: String?): Boolean {
        if (!isSignedIn())
            return false
        val response = realm.query<SignedInUserSchema>()
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
    ): SignedInUserSchema? {
        val response = realm.query<SignedInUserSchema>()
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
            //
          _signInFlow.update { false }
        }
        return false
    }

    fun getSingedInUserInfo(): SignedInUserEntityLocal? {
        val response: RealmResults<SignedInUserSchema> = realm.query<SignedInUserSchema>().find()
        if (response.isNotEmpty()) {
            val signedInUserSchema: SignedInUserSchema = response.first()
            val username = signedInUserSchema.username
            val password = signedInUserSchema.password
            if (username != null && password != null) {
                return SignedInUserEntityLocal(
                    username = username,
                    password = password
                )
            }

        }
        return null
    }

    fun observeSignIn(): Flow<Boolean> {
        realm.query<SignedInUserSchema>().find().let { entities ->
            if (entities.isNotEmpty()) {
                return entities.first().asFlow().map {
                    val user = it.obj
                    if (user == null) false
                    else {
                        user.username != null && user.password != null
                    }
                }
            } else {
                return MutableStateFlow(false)
            }

        }

    }

    fun getToken(): String? {
        val response: RealmResults<SignedInUserSchema> = realm.query<SignedInUserSchema>().find()
        if (response.isNotEmpty()) {
            val signedInUserSchema: SignedInUserSchema = response.first()
            val username = signedInUserSchema.username
            val password = signedInUserSchema.password
            if (username != null && password != null) {
                return signedInUserSchema.authToken
            }

        }
        return null
    }

}
/*
Save info  on sign in ,until sign out
 */