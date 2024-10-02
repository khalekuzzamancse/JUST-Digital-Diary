package core.database.apis

import core.database.entity.CredentialEntity

interface AuthApi {
    suspend fun rememberUser(username: String, password: String)
    suspend fun getUser(username: String, password: String):Result<CredentialEntity>
}