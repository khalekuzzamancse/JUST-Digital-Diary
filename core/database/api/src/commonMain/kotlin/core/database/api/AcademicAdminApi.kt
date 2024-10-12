package core.database.api


interface AcademicAdminApi {
    /**
     * - On Success returns  the Json as the following format
     * ```
     * {
     *   "id": 101, //priority
     *   "faculty_id": "FAC123",
     *   "name": "Computer Science",
     *   "department_count": 4
     * }
     * ```
     *
     * - On Failure return the Feedback message either success or failure as the following format
     * ```
     * {
     *   "message": "Added successfully"
     * }
     * ```
     */
    suspend fun getAllFaculties():String
    /**
     * @param json Pass the Json in the format
     * ```
     * {
     *   "priority": 1,
     *   "name": "XYZ"
     * }
     *
     * ```
     * @return Returns the Feedback message either success or failure as the following format
     * ```
     * {
     *   "message": "Added successfully"
     * }
     * ```
     */
    suspend fun addFaculty(json: String):String

    suspend fun addDepartment(json: String):String
    suspend fun getDepartments():String
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
    suspend fun addTeacher(json: String): String

}