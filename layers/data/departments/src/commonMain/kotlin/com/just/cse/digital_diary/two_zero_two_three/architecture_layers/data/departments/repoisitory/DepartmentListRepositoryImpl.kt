package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.departments.repoisitory

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.departments.data_sources.remote.DepartmentListRemoteDataSource
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.departments.model.DepartmentListResponseModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.departments.repoisitory.DepartmentListRepository

class DepartmentListRepositoryImpl(
    private val token: String?,
) : DepartmentListRepository {
    override suspend fun getDepartment(facultyId: String): DepartmentListResponseModel {

        val response = DepartmentListRemoteDataSource(
            token = token,
            facultyId = facultyId
        ).getDepartments()
        if (response.isSuccess) {
            response.result?.let { dto ->
                return DepartmentListResponseModel.Success(data = dto.data.map { it.toModel() })
            }
            return DepartmentListResponseModel.Success(data = emptyList())
        } else
            return DepartmentListResponseModel.Failure(reason = response.reason)
    }
}