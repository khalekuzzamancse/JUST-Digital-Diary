package core.database.monggodb.presentation

interface DepartmentApi {
    suspend fun insert(facultyId:String,json: String): String

    /**List of department as Json*/
    suspend fun read():String
    /**List of department as Json*/
    suspend fun readUnderFaculty(facultyId:String):String
    suspend fun readById(id:String):String
}