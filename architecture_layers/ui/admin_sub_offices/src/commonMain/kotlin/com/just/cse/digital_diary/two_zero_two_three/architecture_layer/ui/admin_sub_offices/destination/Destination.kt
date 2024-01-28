package com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_sub_offices.destination

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_sub_offices.component.sub_office_list.SubOfficeList
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_sub_offices.component.sub_office_list.event.SubOfficeListEvent
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_sub_offices.destination.event.SubOfficeDestinationEvent
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_sub_offices.destination.viewmodel.SubOfficeListDestinationViewModel

@Composable
fun SubOfficeListDestination(
    modifier: Modifier = Modifier,
    viewModel: SubOfficeListDestinationViewModel,
    onExitRequest:()->Unit,
) {
    SubOfficeList(
        modifier = modifier,
        onEvent = {
            if (it is SubOfficeListEvent.DismissRequest){
                onExitRequest()
            }
            else
                viewModel.onEvent(it)
        },
        state = viewModel.state.collectAsState().value.subOfficeListState
    )

}