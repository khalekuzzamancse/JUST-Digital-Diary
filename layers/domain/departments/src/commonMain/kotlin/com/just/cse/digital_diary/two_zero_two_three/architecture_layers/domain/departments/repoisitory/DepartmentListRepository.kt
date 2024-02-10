package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.departments.repoisitory

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.departments.model.DepartmentListResponseModel

interface DepartmentListRepository{

    suspend fun getDepartment(facultyId:String): DepartmentListResponseModel

}