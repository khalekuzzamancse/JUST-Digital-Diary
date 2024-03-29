package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.faculties.repoisitory

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.faculties.data_sources.remote.FacultyListRemoteDataSource
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.faculties.model.FacultyListResponseModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.faculties.repoisitory.FacultyListRepository
import kotlinx.coroutines.delay

class FacultyListRepositoryImpl(
    private val token: String?
):FacultyListRepository {
    override suspend fun getFaculties(): FacultyListResponseModel {
      val response=  FacultyListRemoteDataSource(token).getFaculties()
        if (response.isSuccess){
           response.result?.let {dto->
             return FacultyListResponseModel.Success(data = dto.data.map { it.toModel() })
           }
            return   FacultyListResponseModel.Success(data = emptyList())
       }
        else
            return FacultyListResponseModel.Failure(reason = response.reason)


    }
}