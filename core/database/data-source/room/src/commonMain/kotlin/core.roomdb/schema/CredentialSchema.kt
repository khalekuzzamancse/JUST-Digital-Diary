package core.roomdb.schema

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "credential_table")
data class CredentialSchema(
    @PrimaryKey val id: Int = 0, // Single instance with fixed ID (0)
    val username: String,
    val password: String
)