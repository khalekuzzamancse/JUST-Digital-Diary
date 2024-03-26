package faculty.data.teacher.repoisitory

import common.di.AuthTokenFactory
import faculty.data.teacher.data_sources.remote.TeacherListRemoteDataSource
import faculty.data.teacher.entity.TeacherListEntity
import faculty.domain.teacher.model.TeacherModel
import faculty.domain.teacher.repoisitory.TeacherListRepository

class TeacherListRepositoryImpl : TeacherListRepository {
    override suspend fun getTeachers(deptId: String): Result<List<TeacherModel>> {
        val token= AuthTokenFactory.retrieveToken().getOrNull()
        val response = TeacherListRemoteDataSource(token, deptId).getTeachers()
        return if (response.isSuccess) onSuccess(response.getOrNull()) else onFailure(response.exceptionOrNull())
    }

    private fun onSuccess(entity: TeacherListEntity?): Result<List<TeacherModel>> {
        return if (entity == null)
            Result.failure(Throwable("Success but dept list is NULL at , FacultyListRepositoryImpl"))
        else
            Result.success(
                entity.facultyMembers.map { teacher ->
                    TeacherModel(
                        name = teacher.name,
                        email = teacher.email,
                        additionalEmail = teacher.additional_email ?: "",
                        profileImageLink = teacher.profile_image ?: "",
                        achievements = teacher.achievement,
                        phone = teacher.phone ?: "",
                        designations = teacher.designations.toString(),
                        deptName = teacher.department_name,
                        deptSortName = teacher.shortname,
                        roomNo = teacher.room_no.toString()
                    )
                })


    }

    private fun onFailure(exception: Throwable?): Result<List<TeacherModel>> {
        val ex =
            exception ?: Throwable("Reason is null at ,FacultyListRepositoryImpl")
        return Result.failure(ex)
    }
}