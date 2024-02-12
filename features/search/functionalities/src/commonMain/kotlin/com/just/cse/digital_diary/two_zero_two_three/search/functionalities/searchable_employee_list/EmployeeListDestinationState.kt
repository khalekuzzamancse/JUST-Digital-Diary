package com.just.cse.digital_diary.two_zero_two_three.search.functionalities.searchable_employee_list


data class EmployeeListDestinationState(
    val isLoading: Boolean = false,
    val message: String?=null,
    val employee:List<Employee> = emptyList()
)
