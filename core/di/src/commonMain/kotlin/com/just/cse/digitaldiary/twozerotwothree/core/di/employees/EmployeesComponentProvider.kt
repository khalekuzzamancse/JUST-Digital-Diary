package com.just.cse.digitaldiary.twozerotwothree.core.di.employees

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.repoisitory.EmployeesRepositoryImpl

object EmployeesComponentProvider {
    fun getEmployeeRepository():EmployeesRepositoryImpl{
        return EmployeesRepositoryImpl()
    }
}