package com.just.cse.digital_diary.two_zero_two_three.department.destinations.teacher_list

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.teachers.repoisitory.TeacherListRepositoryImpl
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.teachers.destination.TeacherListDestination
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.teachers.destination.viewmodel.TeacherListViewModel

@Composable
fun TeacherList(
    viewModel: TeacherListViewModel,
) {
    TeacherListDestination(
        viewModel = viewModel
    )


}