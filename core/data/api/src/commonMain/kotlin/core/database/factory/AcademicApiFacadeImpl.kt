package core.database.factory

import core.data.entity.academic.DepartmentReadEntity
import core.data.entity.academic.DepartmentWriteEntity
import core.data.entity.academic.FacultyReadEntity
import core.data.entity.academic.FacultyWriteEntity
import core.data.entity.academic.TeacherReadEntity
import core.data.entity.academic.TeacherWriteEntity
import core.database.api.AcademicApiFacade
import core.database.api.RemoteCacheHelper.fetchFromRemoteOrCache
import domain.api.AcademicCacheApi
import domain.api.AcademicRemoteApi

class AcademicApiFacadeImpl(
    private val remoteApi: AcademicRemoteApi?,
    private val cacheApi: AcademicCacheApi
) : AcademicApiFacade {

    override suspend fun readFacultiesOrThrow(): List<FacultyReadEntity> {

        return fetchFromRemoteOrCache(
            remoteApi = remoteApi,
            remoteCall = {
               remoteApi!!.readFacultiesOrThrow()
            },
            cacheCall = { cacheApi.readFacultiesOrThrow() },
            cacheUpdate = {
             //   cacheApi.insertFacultiesOrThrow(it)
            }
        )
    }

    override suspend fun readDeptsOrThrow(facultyId: String): List<DepartmentReadEntity> {
        return fetchFromRemoteOrCache(
            remoteApi = remoteApi,
            remoteCall = { remoteApi!!.readDeptsOrThrow(facultyId) },
            cacheCall = { cacheApi.readDeptsOrThrow(facultyId) },
            cacheUpdate = { cacheApi.insertDeptsOrThrow(facultyId, it) }
        )
    }

    override suspend fun readTeachersOrThrow(deptId: String): List<TeacherReadEntity> {
        return fetchFromRemoteOrCache(
            remoteApi = remoteApi,
            remoteCall = { remoteApi!!.readTeachersOrThrow(deptId) },
            cacheCall = { cacheApi.readTeachersOrThrow(deptId) },
            cacheUpdate = { cacheApi.insertTeachersOrThrow(deptId, it) }
        )
    }

    // Other methods remain unchanged or TODO
    override suspend fun readFacultyByIdOrThrow(id: String) = TODO()
    override suspend fun readAllDeptOrThrow() = TODO()
    override suspend fun readDeptOrThrow(id: String) = TODO()
    override suspend fun readAllTeacherOrThrow() = TODO()
    override suspend fun readTeacherOrThrow(teacherId: String) = TODO()
    override suspend fun insertFacultyOrThrow(entity: FacultyWriteEntity) = TODO()
    override suspend fun insertFacultiesOrThrow(entities: List<FacultyWriteEntity>) = TODO()
    override suspend fun insertDeptOrThrow(facultyId: String, entity: DepartmentWriteEntity) =
        TODO()

    override suspend fun insertDeptsOrThrow(
        facultyId: String,
        entities: List<DepartmentWriteEntity>
    ) = TODO()

    override suspend fun insertTeacherOrThrow(deptId: String, entity: TeacherWriteEntity) = TODO()
    override suspend fun insertTeachersOrThrow(deptId: String, entities: List<TeacherWriteEntity>) =
        TODO()

    override suspend fun updateFacultyOrThrow(facultyId: String, entity: FacultyWriteEntity) =
        TODO()

    override suspend fun updateDeptOrThrow(deptId: String, entity: DepartmentWriteEntity) = TODO()
    override suspend fun updateTeacherOrThrow(teacherId: String, entity: TeacherWriteEntity) =
        TODO()

    override suspend fun deleteFacultyOrThrow(id: String) = TODO()
    override suspend fun deleteDepartmentOrThrow(id: String) = TODO()
    override suspend fun deleteTeacherOrThrow(id: String) = TODO()
}
