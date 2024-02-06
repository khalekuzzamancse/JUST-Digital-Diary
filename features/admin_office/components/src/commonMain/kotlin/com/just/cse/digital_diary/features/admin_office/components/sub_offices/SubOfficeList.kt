package com.just.cse.digital_diary.features.admin_office.components.sub_offices

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_sub_offices.component.sub_office_list.SubOfficeListDestination
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_sub_offices.component.sub_office_list.state.SubOfficeListState
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_sub_offices.event.SubOfficeDestinationEvent

@Composable
fun AdminSubOfficeList(
    state: SubOfficeListState,
    onEvent: (SubOfficeDestinationEvent)->Unit,
) {

    SubOfficeListDestination(
        state = state,
        onEvent = onEvent
    )

}