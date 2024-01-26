package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.repoisitory

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.model.AboutUsResponseModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.model.VCInfoResponseModel

interface OtherInfoRepository{
    suspend fun getVCInfo(): VCInfoResponseModel
    suspend fun getAboutUs(): AboutUsResponseModel

}