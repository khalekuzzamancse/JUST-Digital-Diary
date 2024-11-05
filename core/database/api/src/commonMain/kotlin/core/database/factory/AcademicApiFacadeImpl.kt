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
            remoteCall = { remoteApi!!.readFacultiesOrThrow() },
            cacheCall = { cacheApi.readFacultiesOrThrow() },
            cacheUpdate = { cacheApi.insertFacultiesOrThrow(it) }
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
    override suspend fun insertDeptOrThrow(facultyId: String, entity: DepartmentWriteEntity) = TODO()
    override suspend fun insertDeptsOrThrow(facultyId: String, entities: List<DepartmentWriteEntity>) = TODO()
    override suspend fun insertTeacherOrThrow(deptId: String, entity: TeacherWriteEntity) = TODO()
    override suspend fun insertTeachersOrThrow(deptId: String, entities: List<TeacherWriteEntity>) = TODO()
    override suspend fun updateFacultyOrThrow(facultyId: String, entity: FacultyWriteEntity) = TODO()
    override suspend fun updateDeptOrThrow(deptId: String, entity: DepartmentWriteEntity) = TODO()
    override suspend fun updateTeacherOrThrow(teacherId: String, entity: TeacherWriteEntity) = TODO()
    override suspend fun deleteFacultyOrThrow(id: String) = TODO()
    override suspend fun deleteDepartmentOrThrow(id: String) = TODO()
    override suspend fun deleteTeacherOrThrow(id: String) = TODO()
}

//class AcademicApiFacadeImpl(
//    private val remoteApi: AcademicRemoteApi?,
//    private val cacheApi: AcademicCacheApi
//) : AcademicApiFacade {
//
//
//    override suspend fun readFacultiesOrThrow(): List<FacultyReadEntity> {
//        if (remoteApi==null)  return cacheApi.readFacultiesOrThrow()
//
//        try {
//            val response= remoteApi.readFacultiesOrThrow()
//            //If remote give response then update the cache
//           return try {
//               cacheApi.insertFacultiesOrThrow(response)
//                response
//            }
//            catch (cacheInsertionEx:Exception){
//                //If insertion to cache failed should return the remote response
//                response
//            }
//        }
//        catch (serverException:Exception){
//            //If server failed should from  the cache
//         return  try {
//                 cacheApi.readFacultiesOrThrow()
//            }
//            catch (cacheEx:Exception){
//                //If throws serverException,right now propagating the server serverException
//                throw  serverException
//            }
//        }
//
//    }
//
//
//
//    override suspend fun readDeptsOrThrow(facultyId: String): List<DepartmentReadEntity> {
//        if (remoteApi==null)  return cacheApi.readDeptsOrThrow(facultyId)
//
//        try {
//            val response= remoteApi.readDeptsOrThrow(facultyId)
//            //If remote give response then update the cache
//            return try {
//                cacheApi.insertDeptsOrThrow(facultyId,response)
//                response
//            }
//            catch (cacheInsertionEx:Exception){
//                //If insertion to cache failed should return the remote response
//                response
//            }
//        }
//        catch (serverException:Exception){
//            //If server failed should from  the cache
//            return  try {
//                cacheApi.readDeptsOrThrow(facultyId)
//            }
//            catch (cacheEx:Exception){
//                //If throws serverException,right now propagating the server serverException
//                throw  serverException
//            }
//        }
//    }
//
//    override suspend fun readTeachersOrThrow(deptId: String): List<TeacherReadEntity> {
//        if (remoteApi==null)  return cacheApi.readTeachersOrThrow(deptId)
//
//        try {
//            val response= remoteApi.readTeachersOrThrow(deptId)
//            //If remote give response then update the cache
//            return try {
//                cacheApi.insertTeachersOrThrow(deptId,response)
//                response
//            }
//            catch (cacheInsertionEx:Exception){
//                //If insertion to cache failed should return the remote response
//                response
//            }
//        }
//        catch (serverException:Exception){
//            //If server failed should from  the cache
//            return  try {
//                cacheApi.readTeachersOrThrow(deptId)
//            }
//            catch (cacheEx:Exception){
//                //If throws serverException,right now propagating the server serverException
//                throw  serverException
//            }
//        }
//    }
//
//
//
//    override suspend fun readFacultyByIdOrThrow(id: String) = TODO("Not yet implemented")
//    override suspend fun readAllDeptOrThrow() = TODO("Not yet implemented")
//    override suspend fun readDeptOrThrow(id: String) = TODO("Not yet implemented")
//    override suspend fun readAllTeacherOrThrow() = TODO("Not yet implemented")
//    override suspend fun readTeacherOrThrow(teacherId: String) = TODO("Not yet implemented")
//    override suspend fun insertFacultyOrThrow(entity: FacultyWriteEntity) =
//        TODO("Not yet implemented")
//
//    override suspend fun insertFacultiesOrThrow(entities: List<FacultyWriteEntity>) =
//        TODO("Not yet implemented")
//
//    override suspend fun insertDeptOrThrow(facultyId: String, entity: DepartmentWriteEntity) =
//        TODO("Not yet implemented")
//
//    override suspend fun insertDeptsOrThrow(
//        facultyId: String,
//        entities: List<DepartmentWriteEntity>
//    ) = TODO("Not yet implemented")
//
//    override suspend fun insertTeacherOrThrow(deptId: String, entity: TeacherWriteEntity) =
//        TODO("Not yet implemented")
//
//    override suspend fun insertTeachersOrThrow(deptId: String, entities: List<TeacherWriteEntity>) =
//        TODO("Not yet implemented")
//
//    override suspend fun updateFacultyOrThrow(facultyId: String, entity: FacultyWriteEntity) =
//        TODO("Not yet implemented")
//
//    override suspend fun updateDeptOrThrow(deptId: String, entity: DepartmentWriteEntity) =
//        TODO("Not yet implemented")
//
//    override suspend fun updateTeacherOrThrow(teacherId: String, entity: TeacherWriteEntity) =
//        TODO("Not yet implemented")
//
//    override suspend fun deleteFacultyOrThrow(id: String) = TODO("Not yet implemented")
//    override suspend fun deleteDepartmentOrThrow(id: String) = TODO("Not yet implemented")
//    override suspend fun deleteTeacherOrThrow(id: String) = TODO("Not yet implemented")
//}
