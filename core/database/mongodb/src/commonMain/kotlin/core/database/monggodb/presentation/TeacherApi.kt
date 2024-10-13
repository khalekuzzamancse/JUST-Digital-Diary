package core.database.monggodb.presentation

interface TeacherApi {
    /**
     * ```
     * {
     *   "deptId": "CSE101",
     *   "priority": "High",
     *   "name": "Dr. John Doe",
     *   "email": "johndoe@example.com",
     *   "additionalEmail": "john.doe@university.com",
     *   "achievements": "Published 10 research papers in AI",
     *   "phone": "+1234567890",
     *   "designations": "Professor",
     *   "roomNo": "Room 201"
     * }
     *
     * ```
     */
    suspend fun insert(deptId: String,json: String): String

    /**@return All teacher of all departments as Json array*/
    suspend fun readAll(): String
    /**@return All teacher of all departments as Json array*/
    suspend fun readUnderDept(deptId:String): String
    suspend fun readById(teacherId:String): String
}