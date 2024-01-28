package com.just.cse.digital_diary.features.admin_office.sub_offices

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_sub_offices.destination.SubOfficeListDestination
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_sub_offices.destination.viewmodel.SubOfficeListDestinationViewModel
import com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_sub_offices.repoisitory.AdminSubOfficeListRepositoryImpl

@Composable
 fun AdminSubOffices(
    officeId: String,
    onEmployeeListRequest:(String)->Unit,
    onExitRequest:()->Unit,
) {
    val viewModel = remember {
        SubOfficeListDestinationViewModel(
            repository = AdminSubOfficeListRepositoryImpl(officeId)
        )
    }
    LaunchedEffect(Unit){
        viewModel.changeSubOfficeId(officeId)
        viewModel.selectedSubOfficeId.collect{
            if (it!=null){
                onEmployeeListRequest(it)
            }
        }
    }
    SubOfficeListDestination(
        viewModel = viewModel,
        onExitRequest = onExitRequest
    )

}