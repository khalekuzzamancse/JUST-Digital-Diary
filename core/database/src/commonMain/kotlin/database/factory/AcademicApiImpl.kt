package database.factory

import database.apis.AcademicApi
import database.dao.DepartmentDao
import database.dao.FacultyDao
import database.dao.FacultyMemberDao
import database.dto.EntityToSchemaMapper
import database.dto.SchemaToEntityMapper
import database.entity.DepartmentEntity
import database.entity.FacultyEntity
import database.entity.FacultyMemberEntity

class AcademicApiImpl internal  constructor(
    private val facultyDao: FacultyDao,
    private val departmentDao: DepartmentDao,
    private val facultyMemberDao: FacultyMemberDao
) : AcademicApi {

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
            val facultySchemas = faculties.map { EntityToSchemaMapper.fromFacultyEntity(it) }
            facultyDao.upsertFaculties(facultySchemas)
        } catch (e: Exception) {
            throw Exception("Failed to add faculties", e)
        }
    }

    override suspend fun addDepartments(departments: List<DepartmentEntity>) {
        try {
            val departmentSchemas = departments.map { EntityToSchemaMapper.fromDepartmentSchemaEntity(it) }
            departmentDao.upsertDepartments(departmentSchemas)
        } catch (e: Exception) {
            throw Exception("Failed to add departments", e)
        }
    }

    override suspend fun addFacultyMembers(facultyMembers: List<FacultyMemberEntity>) {
        try {
            val facultyMemberSchemas = facultyMembers.map { EntityToSchemaMapper.fromFacultyMemberEntity(it) }
            facultyMemberDao.upsertFacultyMembers(facultyMemberSchemas)
        } catch (e: Exception) {
            throw Exception("Failed to add faculty members", e)
        }
    }
}
