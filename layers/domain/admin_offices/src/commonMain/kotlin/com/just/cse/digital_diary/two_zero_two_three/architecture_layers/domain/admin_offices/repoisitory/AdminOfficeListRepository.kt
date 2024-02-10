package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_offices.repoisitory

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_offices.model.AdminOfficeListResponseModel

interface AdminOfficeListRepository{

    suspend fun getAdminOffices(): AdminOfficeListResponseModel

}