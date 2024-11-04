package core.roomdb.factory
import core.data.entity.academic.*
interface RoomAcademicApi {
    suspend fun readFacultiesOrThrow(): List<FacultyReadEntity>
    suspend fun insertFacultiesOrThrow(entities: List<FacultyReadEntity>)
    suspend fun insertDeptsOrThrow(facultyId: String, entities: List<DepartmentReadEntity>)
    suspend fun readDeptsOrThrow(facultyId: String): List<DepartmentReadEntity>
    suspend fun insertTeachersOrThrow(deptId: String, entities: List<TeacherReadEntity>)
    suspend fun readTeachersOrThrow(deptId: String): List<TeacherReadEntity>
}