package database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import database.schema.TokenSchema

@Dao
internal interface TokenDao {
    
    @Upsert
    suspend fun upsertToken(tokenEntity: TokenSchema)

    @Query("SELECT * FROM token_table WHERE id = 0 LIMIT 1")
    suspend fun getToken(): TokenSchema? // Retrieve the token (null if not present)

    @Query("DELETE FROM token_table")
    suspend fun clearToken() // Clears the token (useful for logging out)
}