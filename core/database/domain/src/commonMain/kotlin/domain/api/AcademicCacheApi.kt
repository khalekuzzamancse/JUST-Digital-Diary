package domain.api
import core.data.entity.academic.*

/**
 * Must match the primary key such FacultyId,deptId,TeacherId, etc with remote
 * ReadEntity that is why taking read entity,if not match or use own primary key or
 * faculty id,deptId,teacherId that does not match with remote then though data will
 * store to cache but can not retrieve because of query parameter not match
 */
interface AcademicCacheApi {
    suspend fun readFacultiesOrThrow(): List<FacultyReadEntity>
    suspend fun insertFacultiesOrThrow(entities: List<FacultyReadEntity>)
    suspend fun insertDeptsOrThrow(facultyId: String, entities: List<DepartmentReadEntity>)
    suspend fun readDeptsOrThrow(facultyId: String): List<DepartmentReadEntity>
    suspend fun insertTeachersOrThrow(deptId: String, entities: List<TeacherReadEntity>)
    suspend fun readTeachersOrThrow(deptId: String): List<TeacherReadEntity>
}