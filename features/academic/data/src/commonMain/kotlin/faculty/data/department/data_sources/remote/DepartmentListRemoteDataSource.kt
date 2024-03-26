package faculty.data.department.data_sources.remote

import core.network.get.Header
import core.network.get.getRequest
import faculty.data.PackageLevelAccess
import faculty.data.department.data_sources.remote.entity.DepartmentInfoEntity
import faculty.data.department.data_sources.remote.entity.DepartmentListEntity

@OptIn(PackageLevelAccess::class)//Okay to use within the same parent package
internal class DepartmentListRemoteDataSource(
    private val token: String?,
    facultyId: String
) {
    private val baseUrl = "https://diary.rnzgoldenventure.com/api/get/departments/$facultyId"

    suspend fun getDepartments(): Result<DepartmentListEntity> {
        if (token == null)
            return Result.failure(Throwable("Token is Null"))
        val header = Header(key = "Authorization", value = token)
        return getRequest<DepartmentListEntity>(url = baseUrl, header = header)
    }

}
//Okay to use within the same parent package
@OptIn(PackageLevelAccess::class)
private fun createDummyDepartmentInfoList(): List<DepartmentInfoEntity> {
    return listOf(
        DepartmentInfoEntity(1, "DEPT001", "Computer Science", "CS"),
        DepartmentInfoEntity(2, "DEPT002", "Mathematics", "Math"),
        DepartmentInfoEntity(3, "DEPT003", "Physics", "Phys")

    )
}