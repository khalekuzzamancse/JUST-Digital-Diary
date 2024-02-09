package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.repoisitory

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.model.EmployeeModel

interface EmployeesRepository{
    suspend fun getEmployees():Result<List<EmployeeModel>>

}