package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.repoisitory

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.model.AdminOfficersListResponseModel

interface AdminOfficerListRepository{

    suspend fun getOfficers(subOfficeId:String): AdminOfficersListResponseModel

}