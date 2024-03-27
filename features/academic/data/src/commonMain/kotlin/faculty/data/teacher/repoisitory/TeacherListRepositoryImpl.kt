package faculty.data.teacher.repoisitory

import common.di.AuthTokenFactory
import core.network.netManagerProvider
import database.local.api.AcademicAPIs
import database.local.schema.DesignationEntityLocal
import database.local.schema.TeacherEntityLocal
import faculty.data.teacher.data_sources.remote.TeacherListRemoteDataSource
import faculty.data.teacher.entity.TeacherListEntity
import faculty.domain.teacher.model.TeacherModel
import faculty.domain.teacher.repoisitory.TeacherListRepository
import java.util.UUID

class TeacherListRepositoryImpl : TeacherListRepository {
    override suspend fun getTeachers(deptId: String): Result<List<TeacherModel>> {
        if (!netManagerProvider().isInternetAvailable()) {
            val localData = AcademicAPIs
                .retrieveTeachers(deptId)
                .getOrDefault(emptyList())
                .map { it.toModel() }
                .sortedBy { it.id }
            println(localData)
            return Result.success(localData)

        }
        val token = AuthTokenFactory.retrieveToken().getOrNull()
        val response = TeacherListRemoteDataSource(token, deptId).getTeachers()
        return if (response.isSuccess) onSuccess(deptId, response.getOrNull()) else onFailure(
            response.exceptionOrNull()
        )
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
            addToLocalDatabase(deptId, result)
            Result.success(result)
        }


    }

    private fun onFailure(exception: Throwable?): Result<List<TeacherModel>> {
        val ex =
            exception ?: Throwable("Reason is null at ,FacultyListRepositoryImpl")
        return Result.failure(ex)
    }

    private suspend fun addToLocalDatabase(deptId: String, entities: List<TeacherModel>) {
        val models = entities.map {
            TeacherEntityLocal(
                uid = UUID.randomUUID().toString(),
                name = it.name,
                email = it.email,
                achievement = it.achievements,
                additionalEmail = it.additionalEmail,
                phone = it.phone,
                profileImage = it.profileImageLink,
                roomNo = it.roomNo,
                designations = listOf(DesignationEntityLocal(it.designations)),
                deptId = deptId,
                departmentName = it.deptName,
                shortName = it.deptSortName,
                id = it.id
            )
        }
        AcademicAPIs.addTeachers(models)
    }
}
private fun TeacherEntityLocal.toModel()=   TeacherModel(
    id=this.id,
    name = this.name,
    email = this.email,
    additionalEmail = this.additionalEmail ?: "",
    profileImageLink = this.profileImage ?: "",
    achievements = this.achievement,
    phone = this.phone ?: "",
    designations = this.designations.toString(),
    deptName = this.departmentName,
    deptSortName = this.shortName,
    roomNo = this.roomNo
)