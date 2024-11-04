package core.database.api
import  core.data.entity.academic.*
interface AcademicApiFacade {
    suspend fun readFacultiesOrThrow(): List<FacultyReadEntity>
    suspend fun readFacultyByIdOrThrow(id: String): FacultyReadEntity
    suspend fun readAllDeptOrThrow(): List<DepartmentReadEntity>
    suspend fun readDeptsOrThrow(facultyId: String): List<DepartmentReadEntity>
    suspend fun readDeptOrThrow(id: String): DepartmentReadEntity
    suspend fun readAllTeacherOrThrow(): List<TeacherReadEntity>
    suspend fun readTeachersOrThrow(deptId: String): List<TeacherReadEntity>
    suspend fun readTeacherOrThrow(teacherId: String): TeacherReadEntity

    suspend fun insertFacultyOrThrow(entity: FacultyWriteEntity)
    suspend fun insertFacultiesOrThrow(entities: List<FacultyWriteEntity>)
    suspend fun insertDeptOrThrow(facultyId: String, entity: DepartmentWriteEntity)
    suspend fun insertDeptsOrThrow(facultyId: String, entities: List<DepartmentWriteEntity>)
    suspend fun insertTeacherOrThrow(deptId: String, entity: TeacherWriteEntity)
    suspend fun insertTeachersOrThrow(deptId: String, entities: List<TeacherWriteEntity>)

    suspend fun updateFacultyOrThrow(facultyId: String, entity: FacultyWriteEntity)
    suspend fun updateDeptOrThrow(deptId: String, entity: DepartmentWriteEntity)
    suspend fun updateTeacherOrThrow(teacherId: String, entity: TeacherWriteEntity)

    suspend fun deleteFacultyOrThrow(id: String)
    suspend fun deleteDepartmentOrThrow(id: String)
    suspend fun deleteTeacherOrThrow(id: String)

}