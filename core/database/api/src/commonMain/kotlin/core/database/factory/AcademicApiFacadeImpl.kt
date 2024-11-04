package core.database.factory

import core.data.entity.academic.DepartmentReadEntity
import core.data.entity.academic.DepartmentWriteEntity
import core.data.entity.academic.FacultyReadEntity
import core.data.entity.academic.FacultyWriteEntity
import core.data.entity.academic.TeacherReadEntity
import core.data.entity.academic.TeacherWriteEntity
import core.database.api.AcademicApiFacade
import core.database.api.fetchWithCache
import core.roomdb.factory.RoomAcademicApi
import domain.api.AcademicApi

class AcademicApiFacadeImpl(
    private val remoteApi: AcademicApi?,
    private val cacheApi: RoomAcademicApi
) : AcademicApiFacade {
    private val notConnectedRemote = remoteApi == null
    override suspend fun readFacultiesOrThrow(): List<FacultyReadEntity> {
        return fetchWithCache(
            notConnectedRemote = notConnectedRemote,
            remoteFetch = { remoteApi!!.readFacultiesOrThrow() },
            cacheFetch = { cacheApi.readFacultiesOrThrow() },
            cacheInsert = { entities -> cacheApi.insertFacultiesOrThrow(entities) },

        )

    }

    override suspend fun readFacultyByIdOrThrow(id: String): FacultyReadEntity {
        TODO("Not yet implemented")
    }

    override suspend fun readAllDeptOrThrow(): List<DepartmentReadEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun readDeptsOrThrow(facultyId: String): List<DepartmentReadEntity> {
        return fetchWithCache(
            remoteFetch = { remoteApi!!.readDeptsOrThrow(facultyId) },
            cacheFetch = { cacheApi.readDeptsOrThrow(facultyId) },
            cacheInsert = { entities -> cacheApi.insertDeptsOrThrow(facultyId, entities) },
            notConnectedRemote = notConnectedRemote
        )

    }

    override suspend fun readDeptOrThrow(id: String): DepartmentReadEntity {
        TODO("Not yet implemented")
    }

    override suspend fun readAllTeacherOrThrow(): List<TeacherReadEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun readTeachersOrThrow(deptId: String): List<TeacherReadEntity> {
        return fetchWithCache(
            remoteFetch = { remoteApi!!.readTeachersOrThrow(deptId) },
            cacheFetch = { cacheApi.readTeachersOrThrow(deptId) },
            cacheInsert = { entities -> cacheApi.insertTeachersOrThrow(deptId, entities) },
            notConnectedRemote = notConnectedRemote
        )
    }

    override suspend fun readTeacherOrThrow(teacherId: String): TeacherReadEntity {
        TODO("Not yet implemented")
    }

    override suspend fun insertFacultyOrThrow(entity: FacultyWriteEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun insertFacultiesOrThrow(entities: List<FacultyWriteEntity>) {
        TODO("Not yet implemented")
    }

    override suspend fun insertDeptOrThrow(facultyId: String, entity: DepartmentWriteEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun insertDeptsOrThrow(
        facultyId: String,
        entities: List<DepartmentWriteEntity>
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun insertTeacherOrThrow(deptId: String, entity: TeacherWriteEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun insertTeachersOrThrow(deptId: String, entities: List<TeacherWriteEntity>) {
        TODO("Not yet implemented")
    }

    override suspend fun updateFacultyOrThrow(facultyId: String, entity: FacultyWriteEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun updateDeptOrThrow(deptId: String, entity: DepartmentWriteEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun updateTeacherOrThrow(teacherId: String, entity: TeacherWriteEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFacultyOrThrow(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteDepartmentOrThrow(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTeacherOrThrow(id: String) {
        TODO("Not yet implemented")
    }
}