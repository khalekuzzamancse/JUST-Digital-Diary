package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.teachers.repoisitory

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.teachers.model.TeacherListResponseModel

interface TeacherListRepository{

    suspend fun getTeachers(deptId:String): TeacherListResponseModel

}