package domain.model

/**
 * @property json is the updated json that may contain `extra fields`
 * the database `must` direct insert it without any modifications
 */
data class InsertionResult(
    val primaryKey:String,
    val json:String
)
