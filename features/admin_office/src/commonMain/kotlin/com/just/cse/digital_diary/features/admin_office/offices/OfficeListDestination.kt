package com.just.cse.digital_diary.features.admin_office.offices

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.destination.destination.AdminOfficesDestination
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.destination.viewmodel.AdminOfficesDestinationViewModel
import com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_offices.repoisitory.AdminOfficeListRepositoryImpl

@Composable
fun AdminOffices() {
    AdminOfficesDestination(
        viewModel = AdminOfficesDestinationViewModel(
            repository = AdminOfficeListRepositoryImpl()
        )
    )


}