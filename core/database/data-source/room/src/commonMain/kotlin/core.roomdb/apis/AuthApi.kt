package core.roomdb.apis

import core.roomdb.entity.CredentialEntity

interface AuthApi {
    suspend fun rememberUser(username: String, password: String)
    suspend fun getUser(username: String, password: String):Result<CredentialEntity>
}