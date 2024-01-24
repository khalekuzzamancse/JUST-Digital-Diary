package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_sub_offices.repoisitory

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_sub_offices.model.AdminSubOfficeListResponseModel

interface AdminSubOfficeListRepository{

    suspend fun getSubOffices(): AdminSubOfficeListResponseModel

}