package core.database.monggodb.presentation.apis

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
    suspend fun add(json: String): String
}