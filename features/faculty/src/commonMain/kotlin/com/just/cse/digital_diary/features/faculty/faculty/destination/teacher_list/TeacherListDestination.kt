package com.just.cse.digital_diary.features.faculty.faculty.destination.teacher_list

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.teachers.destination.TeacherListDestination
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.teachers.destination.viewmodel.TeacherListViewModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.teachers.repoisitory.TeacherListRepositoryImpl

@Composable
fun TeacherList(

) {
    TeacherListDestination(
        viewModel = TeacherListViewModel(
            repository = TeacherListRepositoryImpl()
        )
    )

}