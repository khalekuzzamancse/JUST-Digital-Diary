package core.roomdb.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import core.roomdb.schema.TokenSchema
import kotlinx.coroutines.flow.Flow

@Dao
internal interface TokenDao {

    // Insert or update (Upsert) a token. Since there is a fixed primary key, only one token will exist.
    @Upsert
    suspend fun upsertToken(token: TokenSchema)

    // Retrieve the token as a Flow to observe changes in real-time
    @Query("SELECT * FROM token_table WHERE id = 0 LIMIT 1")
    fun getToken(): Flow<TokenSchema?>

    // Delete the token
    @Delete
    suspend fun deleteToken(token: TokenSchema)

    // Clear all tokens (though only one should exist, this clears the table)
    @Query("DELETE FROM token_table")
    suspend fun clearAllTokens()
}
