package com.just.cse.digital_diary.two_zero_two_three.department.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.department.local_destinations.CompactScreenLayout
import com.just.cse.digital_diary.two_zero_two_three.department.local_destinations.Screen
import com.just.cse.digital_diary.two_zero_two_three.department.local_destinations.home.Home
import com.just.cse.digital_diary.two_zero_two_three.department.local_destinations.staff_list.DepartmentStaffListDestinationContent
import com.just.cse.digital_diary.two_zero_two_three.department.local_destinations.teacher_list.DepartmentTeacherListDestinationContent
import com.just.cse.digitaldiary.twozerotwothree.data.repository.department_info.DepartmentInfoRepository


//
/*
All department have the shared top and bottom bar.
they must maintain a separate backstack means navigator

 */


//prevent outside module to access it

@Composable
internal fun DepartmentModuleLocalNavGraph(
    departmentId: String,
    onExitRequested: () -> Unit,
) {

    Screen(
        departmentId=departmentId,
        onExitRequested=onExitRequested
    )


}


