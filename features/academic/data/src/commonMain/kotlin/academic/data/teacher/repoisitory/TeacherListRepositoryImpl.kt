package academic.data.teacher.repoisitory

import common.di.AuthTokenFactory
import core.network.netManagerProvider
import academic.data.DependencyFactory
import academic.data.teacher.sources.remote.entity.TeacherListEntity
import faculty.domain.teacher.model.TeacherModel
import faculty.domain.teacher.repoisitory.TeacherListRepository

class TeacherListRepositoryImpl : TeacherListRepository {
    private val localSource= DependencyFactory.teacherLocalDataSource()
    override suspend fun getTeachers(deptId: String): Result<List<TeacherModel>> {
        val remoteSource= DependencyFactory.teacherRemoteDataSource(deptId)
        if (!netManagerProvider().isInternetAvailable()) return  localSource.getTeachers(deptId)
        val token = AuthTokenFactory.retrieveToken().getOrNull()
        return if (token==null) localSource.getTeachers(deptId)
        else{
            val response =remoteSource.getTeachers(token)
            if (response.isSuccess) onSuccess(deptId, response.getOrNull()) else onFailure(
                response.exceptionOrNull()
            )
        }

    }

    private suspend fun onSuccess(
        deptId: String,
        entity: TeacherListEntity?
    ): Result<List<TeacherModel>> {
        return if (entity == null)
            Result.failure(Throwable("Success but dept list is NULL at , FacultyListRepositoryImpl"))
        else {
            val result = entity.facultyMembers.map { teacher ->
                TeacherModel(
                    id = 0, //update later
                    name = teacher.name,
                    email = teacher.email,
                    additionalEmail = teacher.additional_email ?: "",
                    profileImageLink = teacher.profile_image ?: "",
                    achievements = teacher.achievement,
                    phone = teacher.phone ?: "",
                    designations = teacher.designations.toString(),
                    deptName = teacher.department_name,
                    deptSortName = teacher.shortname,
                    roomNo = teacher.room_no
                )
            }
            localSource.addTeachers(deptId,result)
            Result.success(result)
        }


    }

    private fun onFailure(exception: Throwable?): Result<List<TeacherModel>> {
        val ex =
            exception ?: Throwable("Reason is null at ,FacultyListRepositoryImpl")
        return Result.failure(ex)
    }

}
