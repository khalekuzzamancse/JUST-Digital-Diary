package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.faculties.repoisitory

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.faculties.model.FacultyInfoModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.faculties.model.FacultyListResponseModel

interface FacultyListRepository{

    suspend fun getFaculties(): FacultyListResponseModel

}