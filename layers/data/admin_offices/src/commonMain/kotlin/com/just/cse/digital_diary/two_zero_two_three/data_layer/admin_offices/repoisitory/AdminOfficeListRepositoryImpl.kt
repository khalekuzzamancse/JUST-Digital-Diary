package com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_offices.repoisitory

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_offices.model.AdminOfficeListResponseModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_offices.repoisitory.AdminOfficeListRepository
import com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_offices.data_sources.remote.AdminOfficeListRemoteDataSource

class AdminOfficeListRepositoryImpl(
    private val token: String?
) : AdminOfficeListRepository {

    override suspend fun getAdminOffices(): AdminOfficeListResponseModel {
        val response = AdminOfficeListRemoteDataSource(token).getOffices()
        if (response.isSuccess) {
            response.result?.let { dto ->
                return AdminOfficeListResponseModel.Success(data = dto.data.map { it.toModel() })
            }
            return AdminOfficeListResponseModel.Success(data = emptyList())
        } else{
            return AdminOfficeListResponseModel.Failure(reason = response.reason)
        }

    }
}