package core.roomdb.factory

import core.roomdb.apis.AcademicApiRoom
import core.roomdb.dao.DepartmentDao
import core.roomdb.dao.FacultyDao
import core.roomdb.dao.FacultyMemberDao
import core.roomdb.dto.EntityToSchemaMapper
import core.roomdb.dto.SchemaToEntityMapper
import core.roomdb.entity.FacultyEntity
import core.roomdb.entity.FacultyMemberEntity


class AcademicApiImpl internal constructor(
    private val facultyDao: FacultyDao,
    private val departmentDao: DepartmentDao,
    private val facultyMemberDao: FacultyMemberDao
) : AcademicApiRoom {

    @Deprecated("use the  readFacultiesOrThrows to get the exception as custom exception")
    override suspend fun getAllFaculties(): Result<List<FacultyEntity>> {
        return try {
            val facultySchemas = facultyDao.getAllFaculties()
            val facultyEntities =
                facultySchemas.map { SchemaToEntityMapper.fromFacultySchema(it) }
            Result.success(facultyEntities)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun readFacultiesOrThrows(): List<FacultyEntity> {
        return try {
            facultyDao
              .getAllFaculties()
              .map { SchemaToEntityMapper.fromFacultySchema(it) }

        } catch (e: Exception) {
            throw e //TODO:Convert custom exception before throwing
        }
    }

    override suspend fun getDepartmentsByFacultyId(facultyId: String): Result<List<core.roomdb.entity.DepartmentEntity>> {
        return try {
            val departmentSchemas = departmentDao.getDepartmentsByFaculty(facultyId)
            val departmentEntities =
                departmentSchemas.map { SchemaToEntityMapper.fromDepartmentEntity(it) }
            Result.success(departmentEntities)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getFacultyMembersByDeptId(deptId: String): Result<List<FacultyMemberEntity>> {
        return try {
            val facultyMemberSchemas = facultyMemberDao.getFacultyMembersByDeptId(deptId)
            val facultyMemberEntities = facultyMemberSchemas.map {
                SchemaToEntityMapper.fromFacultyMemberSchema(it)
            }
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

    override suspend fun addDepartments(departments: List<core.roomdb.entity.DepartmentEntity>) {
        try {
            val departmentSchemas =
                departments.map { EntityToSchemaMapper.fromDepartmentSchemaEntity(it) }
            departmentDao.upsertDepartments(departmentSchemas)
        } catch (e: Exception) {
            throw Exception("Failed to add departments", e)
        }
    }

    override suspend fun addFacultyMembers(facultyMembers: List<FacultyMemberEntity>) {
        try {
            val facultyMemberSchemas =
                facultyMembers.map { EntityToSchemaMapper.fromFacultyMemberEntity(it) }
            facultyMemberDao.upsertFacultyMembers(facultyMemberSchemas)
        } catch (e: Exception) {
            throw Exception("Failed to add faculty members", e)
        }
    }
}
