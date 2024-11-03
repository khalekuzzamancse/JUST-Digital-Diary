package core.roomdb.factory

import core.roomdb.dao.DepartmentDao
import core.roomdb.dao.FacultyDao
import core.roomdb.dao.FacultyMemberDao
import core.roomdb.withExceptionHandle
import domain.api.AcademicApi
import domain.entity.academic.FacultyReadEntity
import domain.factory.ContractFactory

class AcademicApiImpl2 internal  constructor(
    private val facultyDao: FacultyDao,
    private val departmentDao: DepartmentDao,
    private val facultyMemberDao: FacultyMemberDao
):AcademicApi{

    private val insertionService = ContractFactory.insertionService()
    private val readEntityService = ContractFactory.academicReadEntityService()

    override suspend fun readAllFaculty()=withExceptionHandle {
        val schemas = facultyDao.getAllFaculties()
        val entities =
            schemas.map { schema->
                FacultyReadEntity(
                    priority = schema.id,
                    faculty_id = schema.facultyId,
                    name = schema.name,
                    number_of_dept = 0//TODO:Refactor it later
                )
            }

        readEntityService.parseOrThrow(entities)
    }

    override suspend fun insertFaculty(json: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun readFacultyById(id: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFaculty(id: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun insertDept(facultyId: String, json: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun readAllDept(): String {
        TODO("Not yet implemented")
    }

    override suspend fun deptUnderFaculty(facultyId: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun readDeptById(id: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun deleteDepartment(id: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun insertTeacher(deptId: String, json: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun readAllTeacher(): String {
        TODO("Not yet implemented")
    }

    override suspend fun readTeachersUnderDept(deptId: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun readTeacherById(teacherId: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun updateFaculty(facultyId: String, json: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun updateDept(deptId: String, json: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun updateTeacher(teacherId: String, json: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTeacher(id: String): String {
        TODO("Not yet implemented")
    }
}