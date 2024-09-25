package administration.data.officers.repoisitory

import admin_office.domain.officers.model.AdminOfficerModel
import admin_office.domain.officers.repoisitory.AdminOfficerListRepository
import administration.data.PackageLevelAccess
import administration.data.officers.data_sources.remote.entity.AdminOfficerListEntity


class AdminOfficerListRepositoryImpl : AdminOfficerListRepository {


    @OptIn(PackageLevelAccess::class) // Permits access to "AdminOfficerListEntity" within this package
    override suspend fun getOfficers(subOfficeId: String): Result<List<AdminOfficerModel>> {
//        val token= AuthTokenFactory.retrieveToken().getOrNull()
//        val response = AdminOfficerListRemoteDataSource(
//            token = token,
//            subOfficeId = subOfficeId
//        ).getOfficers()
//        return if (response.isSuccess)
//            onSuccess(response.getOrNull())
//        else
//            onFailure(response.exceptionOrNull())
        TODO()
    }

    @OptIn(PackageLevelAccess::class) // Permits access to "AdminOfficerListEntity" within this package
    private fun onSuccess(entity: AdminOfficerListEntity?): Result<List<AdminOfficerModel>> {
        return if (entity == null)
            Result.failure(Throwable("Success but dept list is NULL at , DepartmentListRepositoryImpl:onSuccess()"))
        else
            Result.success(
                entity.officeMembers.map {
                    AdminOfficerModel(
                        name = it.name,
                        email =it.email,
                        additionalEmail = it.additional_email ?: "",
                        profileImage = it.profile_image ?: "",
                        achievements = it.achievement,
                        phone =it. phone ?: "",
                        designations = it.designations,
                        roomNo = it.room_no

                    )
                }
            )
    }

    private fun onFailure(exception: Throwable?): Result<List<AdminOfficerModel>> {
        val ex = exception ?: Throwable("Reason is null at ,DepartmentListRepositoryImpl")
        return Result.failure(ex)
    }


}