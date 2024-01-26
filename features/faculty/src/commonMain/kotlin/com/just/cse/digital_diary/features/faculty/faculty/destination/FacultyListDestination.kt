package com.just.cse.digital_diary.features.faculty.faculty.destination

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_offices.destination.destination.FacultiesDestination
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_offices.destination.viewmodel.FacultyDestinationViewModel

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