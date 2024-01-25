package com.just.cse.digital_diary.features.faculty.faculty.dd.destination

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.departments.destination.viewmodel.DepartmentDestinationList
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.departments.destination.viewmodel.DepartmentListDestinationViewModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.departments.repoisitory.DepartmentListRepositoryImpl

@Composable
fun DepartmentList(
    facultyId: String,
) {
    DepartmentDestinationList(
        viewModel = DepartmentListDestinationViewModel(
            repository = DepartmentListRepositoryImpl(),
            facultyId = facultyId
        )
    )

}