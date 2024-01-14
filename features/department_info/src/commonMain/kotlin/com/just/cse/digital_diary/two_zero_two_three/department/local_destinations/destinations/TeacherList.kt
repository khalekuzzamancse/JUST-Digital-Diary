package com.just.cse.digital_diary.two_zero_two_three.department.local_destinations.destinations

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.department.department_info.EmployeeList
import com.just.cse.digitaldiary.twozerotwothree.data.data.repository.Employee

@Composable
internal fun DepartmentTeacherListDestinationContent(
    modifier: Modifier = Modifier,
    teachers: List<Employee>,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        EmployeeList(
            employees = teachers
        )


    }


}