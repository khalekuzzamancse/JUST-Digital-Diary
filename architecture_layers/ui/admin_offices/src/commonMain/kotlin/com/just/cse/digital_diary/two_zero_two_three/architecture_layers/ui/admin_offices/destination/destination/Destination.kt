package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.destination.destination

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.destination.viewmodel.AdminOfficesDestinationViewModel

@Composable
fun AdminOfficesDestination(
  viewModel: AdminOfficesDestinationViewModel,
) {


    CompactModeLayout(
        facultyListState = viewModel.state.collectAsState().value.facultyListState,
        onEvent = viewModel::onEvent,
    )

}