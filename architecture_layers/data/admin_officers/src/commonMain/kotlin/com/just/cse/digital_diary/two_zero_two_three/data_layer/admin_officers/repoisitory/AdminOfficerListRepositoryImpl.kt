package com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_officers.repoisitory

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.model.AdminOfficersListResponseModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.repoisitory.AdminOfficerListRepository
import com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_officers.data_sources.remote.AdminOfficerListRemoteDataSource

class AdminOfficerListRepositoryImpl(): AdminOfficerListRepository {

    override suspend fun getOfficers(subOfficeId:String): AdminOfficersListResponseModel {
        val response= AdminOfficerListRemoteDataSource(subOfficeId).getOfficers()
        if (response.isSuccess){
            response.result?.let {dto->
                return AdminOfficersListResponseModel.Success(data =dto.data.map{it.toModel() })
            }
            return   AdminOfficersListResponseModel.Success(data = emptyList())
        }
        else
            return AdminOfficersListResponseModel.Failure(reason = response.reason)
    }
}