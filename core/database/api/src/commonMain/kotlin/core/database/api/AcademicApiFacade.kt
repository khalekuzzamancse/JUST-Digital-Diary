package core.database.api

interface AcademicApiFacade {
    suspend fun insertFaculty(json: String): String
    suspend fun insertDept(facultyId: String, json: String): String
    suspend fun insertTeacher(deptId: String, json: String): String

    suspend fun readAllFaculty(): String
    suspend fun readFacultyById(id: String): String
    suspend fun readDeptById(id: String): String

    suspend fun readAllTeachers(): String
    suspend fun readTeachersUnderDept(deptId: String): String
    suspend fun readTeacherById(teacherId: String): String
    suspend fun readAllDept(): String
    suspend fun readAllDeptUnderFaculty(facultyId: String): String

    suspend fun updateFaculty(facultyId: String, json: String): String
    suspend fun updateDept(deptId: String, json: String): String
    suspend fun updateTeacher(teacherId: String, json: String): String
}
