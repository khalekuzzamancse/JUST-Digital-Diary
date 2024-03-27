package academic.data.department.source.remote

import core.network.get.Header
import core.network.get.getRequest
import academic.data.PackageLevelAccess
import academic.data.department.source.remote.entity.DepartmentListEntity

@OptIn(PackageLevelAccess::class)//Okay to use within the same parent package
internal class DepartmentRemoteDataSourceImpl(
    facultyId: String
) : DepartmentRemoteDataSource {
    private val baseUrl = "https://diary.rnzgoldenventure.com/api/get/departments/$facultyId"
    override suspend fun getDepartments(token: String): Result<DepartmentListEntity> {
        val header = Header(key = "Authorization", value = token)
        return getRequest<DepartmentListEntity>(url = baseUrl, header = header)
    }

}
