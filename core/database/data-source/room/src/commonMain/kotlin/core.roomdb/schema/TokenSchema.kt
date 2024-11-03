package core.roomdb.schema

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * - Act as schema, should not exposes to outer module
 */
@Entity(tableName = "token_table")
internal data class TokenSchema(
    @PrimaryKey val id: Int = 0, // Fixed primary key, ensuring only one token
    val token: String?           // Nullable token field
)