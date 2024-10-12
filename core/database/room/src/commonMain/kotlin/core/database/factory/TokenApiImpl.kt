package core.database.factory

import core.database.schema.TokenSchema
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class TokenApiImpl internal constructor(
    private val tokenDao: core.database.dao.TokenDao
) : core.database.apis.TokenApi {

    // Save or update the token and propagate success/failure using Result<Unit>
    override suspend fun saveToken(token: String): Result<Unit> = withContext(Dispatchers.IO) {
        return@withContext try {
            // Save the token, always using a fixed ID (0) to ensure only one token exists
            val tokenSchema = TokenSchema(token = token)
            tokenDao.upsertToken(tokenSchema)
            Result.success(Unit) // Propagate success
        } catch (e: Exception) {
            Result.failure(e) // Propagate the exception in case of failure
        }
    }

    // Observe the token using Flow
    override fun getToken(): Flow<String?> {
        return tokenDao.getToken() // Returns Flow<TokenSchema?>
            .map { tokenSchema -> tokenSchema?.token } // Map the Flow<TokenSchema?> to Flow<String?>
    }

    // Clear the token and propagate success/failure using Result<Unit>
    override suspend fun clearToken(): Result<Unit> = withContext(Dispatchers.IO) {
        return@withContext try {
            tokenDao.clearAllTokens() // Clear the entire token table
            Result.success(Unit) // Propagate success
        } catch (e: Exception) {
            Result.failure(e) // Propagate the exception in case of failure
        }
    }
}
