package core.database.monggodb.presentation.apis

interface DepartmentApi {
    suspend fun add(json: String): String

    /**
     * List of department as Json
     */
    suspend fun read():String
}