package com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_officers.repoisitory

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.entity.AboutUsResponseModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.entity.VCInfoResponseModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.entity.event_gallery.EventGalleryResponseModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.repoisitory.OtherInfoRepository
import com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_officers.data_sources.remote.RemoteDataSource

class OtherInfoRepositoryImpl: OtherInfoRepository {

    override suspend fun getVCInfo(): VCInfoResponseModel {
       return VCInfoResponseModel.Success(RemoteDataSource().getVCInfo().toModel())
    }

    override suspend fun getAboutUs(): AboutUsResponseModel {
        return AboutUsResponseModel.Success(RemoteDataSource().getAboutUsInfo().toModel())
    }

    override suspend fun getEvents(): EventGalleryResponseModel {
        return EventGalleryResponseModel.Success(RemoteDataSource().getEvents().map { it.toModel() })
    }
}