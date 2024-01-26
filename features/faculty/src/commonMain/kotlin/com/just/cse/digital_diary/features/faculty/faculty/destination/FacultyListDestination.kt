package com.just.cse.digital_diary.features.faculty.faculty.destination

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.departments.destination.viewmodel.DepartmentDestinationList
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.departments.destination.viewmodel.DepartmentListDestinationViewModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.faculties.destination.destination.FacultiesDestination
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.faculties.destination.viewmodel.FacultyDestinationViewModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.departments.repoisitory.DepartmentListRepositoryImpl
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.faculties.repoisitory.FacultyListRepositoryImpl

@Composable
fun FacultyListDestination(
    viewModel: FacultyDestinationViewModel,
    homeContent:@Composable ()->Unit,
) {
    FacultiesDestination(
        viewModel = viewModel,
        homeContent=homeContent
    )

}