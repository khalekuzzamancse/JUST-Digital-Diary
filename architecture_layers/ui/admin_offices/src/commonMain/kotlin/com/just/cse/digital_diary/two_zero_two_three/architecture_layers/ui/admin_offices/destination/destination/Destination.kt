package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.destination.destination

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.destination.layout.CompactModeLayout
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.event.AdminOfficesDestinationEvent
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.office_list.state.OfficeListState

@Composable
fun AdminOfficeList(
    officeListState: OfficeListState,
    onEvent: (AdminOfficesDestinationEvent) -> Unit,
) {
    CompactModeLayout(
        officeListState = officeListState,
        onEvent =onEvent,
        onExitRequest={
            onEvent(AdminOfficesDestinationEvent.ExitRequest)
        }
    )

}