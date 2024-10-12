package core.database.monggodb.presentation.apis

interface FacultyApi {
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
    suspend fun getAllFaculties(): String

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
    suspend fun addFaculty(json: String): String
}