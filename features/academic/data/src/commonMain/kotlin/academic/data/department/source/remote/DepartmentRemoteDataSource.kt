package academic.data.department.source.remote

import academic.data.PackageLevelAccess
import academic.data.department.source.remote.entity.DepartmentListEntity

internal interface DepartmentRemoteDataSource {
    @OptIn(PackageLevelAccess::class)
    suspend fun getDepartments(
        token: String,
    ): Result<DepartmentListEntity>
}