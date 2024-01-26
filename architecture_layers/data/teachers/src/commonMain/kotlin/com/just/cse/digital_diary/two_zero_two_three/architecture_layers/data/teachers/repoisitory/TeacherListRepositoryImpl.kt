package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.teachers.repoisitory

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.teachers.data_sources.remote.TeacherListRemoteDataSource
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.teachers.model.TeacherListResponseModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.teachers.repoisitory.TeacherListRepository

class TeacherListRepositoryImpl(): TeacherListRepository {

    override suspend fun getTeachers(deptId: String): TeacherListResponseModel {
        val response= TeacherListRemoteDataSource(deptId).getTeachers()
        if (response.isSuccess){
            response.result?.let {dto->
                return TeacherListResponseModel.Success(data =dto.data.map{it.toModel() })
            }
            return   TeacherListResponseModel.Success(data = emptyList())
        }
        else
            return TeacherListResponseModel.Failure(reason = response.reason)
    }
}