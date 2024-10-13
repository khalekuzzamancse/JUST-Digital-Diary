package core.database.api

import core.database.monggodb.di.DBFactory

/**
 * - Prevent consumer module to create instance of it
 */
class MongoAcademicAdminApi internal constructor() : AcademicAdminApi {
    override suspend fun readAllFaculty() = DBFactory.facultyApi().readAll()
    override suspend fun insertFaculty(json: String) = DBFactory.facultyApi().insert(json)
    override suspend fun readFacultyById(id: String) = DBFactory.facultyApi().read(id)
    override suspend fun insertDept(facultyId: String,json: String) = DBFactory.departmentApi().insert(facultyId,json)
    override suspend fun getDepartments() = DBFactory.departmentApi().read()
    override suspend fun insertTeacher(deptId: String,json: String) = DBFactory.teacherApi().insert(deptId,json)
    override suspend fun deptUnderFaculty(facultyId: String) =
        DBFactory.departmentApi().readUnderFaculty(facultyId)

    override suspend fun readDeptById(id: String) = DBFactory.departmentApi().readById(id)
    override suspend fun getTeachers() = DBFactory.teacherApi().readAll()
    override suspend fun readTeachersUnderDept(deptId: String) =
        DBFactory.teacherApi().readUnderDept(deptId)

    override suspend fun readTeacherById(teacherId: String) =
        DBFactory.teacherApi().readById(teacherId)
}