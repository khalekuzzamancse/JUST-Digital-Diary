package com.just.cse.digital_diary.features.admin_office.admin_officers

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.admin_officers.destination.AdminOfficerListDestination
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.admin_officers.destination.viewmodel.AdminOfficerListViewModel
import com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_officers.repoisitory.AdminOfficerListRepositoryImpl

@Composable
fun AdminOfficers() {
    AdminOfficerListDestination(
        viewModel = AdminOfficerListViewModel(
            repository = AdminOfficerListRepositoryImpl()
        )
    )
}