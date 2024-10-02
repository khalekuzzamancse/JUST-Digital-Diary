package core.database.apis

import kotlinx.coroutines.flow.Flow

interface TokenApi {
    // Save the token and propagate any errors or success via Result<Unit>
    suspend fun saveToken(token: String): Result<Unit>

    // Observe the token using Flow
    fun getToken(): Flow<String?>

    // Clear the token and propagate any errors or success via Result<Unit>
    suspend fun clearToken(): Result<Unit>
}
