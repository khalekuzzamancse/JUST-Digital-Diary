package com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_sub_offices.repoisitory

import com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_sub_offices.data_sources.remote.AdminSubOfficeListRemoteDataSource
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_sub_offices.model.AdminSubOfficeListResponseModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_sub_offices.repoisitory.AdminSubOfficeListRepository

class AdminSubOfficeListRepositoryImpl(
    private val token:String?
): AdminSubOfficeListRepository {


    override suspend fun getSubOffices(officeId: String): AdminSubOfficeListResponseModel {
        val response= AdminSubOfficeListRemoteDataSource(
            officeId = officeId,
            token = token
        ).getSubOffices()
        if (response.isSuccess){
            response.result?.let {dto->
                return AdminSubOfficeListResponseModel.Success(data =dto.data.map{it.toModel() })
            }
            return   AdminSubOfficeListResponseModel.Success(data = emptyList())
        }
        else
            return AdminSubOfficeListResponseModel.Failure(reason = response.reason)
    }
}