package database.factory

import database.apis.AuthApi
import database.dao.CredentialDao
import database.entity.CredentialEntity
import database.schema.CredentialSchema
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthApiImpl internal  constructor(
    private val credentialDao: CredentialDao
) : AuthApi {

    override suspend fun rememberUser(username: String, password: String) = withContext(Dispatchers.IO) {
        try {
            val credential = CredentialSchema(username = username, password = password)
            credentialDao.upsertCredential(credential)
          //  println("User credentials saved successfully!")
        } catch (_: Exception) {
           // println("Failed to save user credentials: ${e.message}")
        }
    }

    override suspend fun getUser(username: String, password: String): Result<CredentialEntity> = withContext(Dispatchers.IO) {
        return@withContext try {
            val storedCredential = credentialDao.getCredential()
            if (storedCredential != null && storedCredential.username == username && storedCredential.password == password) {
                Result.success(CredentialEntity(username = storedCredential.username, password = storedCredential.password))
            } else {
                Result.failure(Exception("Invalid credentials"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Failed to retrieve user credentials: ${e.message}"))
        }
    }
}
