package core.database.api

import core.database.monggodb.presentation.DBFactory

/**
 * - Prevent consumer module to create instance of it
 */
class MongoAcademicAdminApi internal  constructor():AcademicAdminApi{
    override suspend fun getAllFaculties()= DBFactory.facultyApi().getAllFaculties()
    override suspend fun addFaculty(json: String)=DBFactory.facultyApi().addFaculty(json)
    override suspend fun addDepartment(json: String)=DBFactory.departmentApi().add(json)
    override suspend fun getDepartments()=DBFactory.departmentApi().read()
    override suspend fun addTeacher(json: String)=DBFactory.teacherApi().add(json)
    override suspend fun getTeachers()=DBFactory.teacherApi().read()
}