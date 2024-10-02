package core.database.factory

import core.database.dto.SchemaToEntityMapper
import core.database.entity.DepartmentEntity
import core.database.entity.FacultyEntity
import core.database.entity.FacultyMemberEntity

class AcademicApiImpl internal  constructor(
    private val facultyDao: core.database.dao.FacultyDao,
    private val departmentDao: core.database.dao.DepartmentDao,
    private val facultyMemberDao: core.database.dao.FacultyMemberDao
) : core.database.apis.AcademicApi {

    override suspend fun getAllFaculties(): Result<List<FacultyEntity>> {
        return try {
            val facultySchemas = facultyDao.getAllFaculties()
            val facultyEntities = facultySchemas.map { SchemaToEntityMapper.fromFacultySchema(it) }
            Result.success(facultyEntities)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getDepartmentsByFacultyId(facultyId: String): Result<List<DepartmentEntity>> {
        return try {
            val departmentSchemas = departmentDao.getDepartmentsByFaculty(facultyId)
            val departmentEntities = departmentSchemas.map { SchemaToEntityMapper.fromDepartmentEntity(it) }
            Result.success(departmentEntities)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getFacultyMembersByDeptId(deptId: String): Result<List<FacultyMemberEntity>> {
        return try {
            val facultyMemberSchemas = facultyMemberDao.getFacultyMembersByDeptId(deptId)
            val facultyMemberEntities = facultyMemberSchemas.map { SchemaToEntityMapper.fromFacultyMemberSchema(it) }
            Result.success(facultyMemberEntities)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun addFaculties(faculties: List<FacultyEntity>) {
        try {
            val facultySchemas = faculties.map { core.database.dto.EntityToSchemaMapper.fromFacultyEntity(it) }
            facultyDao.upsertFaculties(facultySchemas)
        } catch (e: Exception) {
            throw Exception("Failed to add faculties", e)
        }
    }

    override suspend fun addDepartments(departments: List<DepartmentEntity>) {
        try {
            val departmentSchemas = departments.map { core.database.dto.EntityToSchemaMapper.fromDepartmentSchemaEntity(it) }
            departmentDao.upsertDepartments(departmentSchemas)
        } catch (e: Exception) {
            throw Exception("Failed to add departments", e)
        }
    }

    override suspend fun addFacultyMembers(facultyMembers: List<FacultyMemberEntity>) {
        try {
            val facultyMemberSchemas = facultyMembers.map { core.database.dto.EntityToSchemaMapper.fromFacultyMemberEntity(it) }
            facultyMemberDao.upsertFacultyMembers(facultyMemberSchemas)
        } catch (e: Exception) {
            throw Exception("Failed to add faculty members", e)
        }
    }
}
