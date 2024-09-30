package database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import database.schema.CredentialSchema

@Dao
interface CredentialDao {

    @Upsert
    suspend fun upsertCredential(credential: CredentialSchema)

    @Query("SELECT * FROM credential_table WHERE id = 0 LIMIT 1")
    suspend fun getCredential(): CredentialSchema? // Retrieve credential (null if not present)

    @Query("DELETE FROM credential_table WHERE id = 0")
    suspend fun clearCredential() // Optional: clears the credential
}
