package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.components

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.components.layout.CompactModeLayout
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.event.AdminOfficesEvent
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.components.office_list.state.OfficeListState

@Composable
fun AdminOfficeList(
    officeListState: OfficeListState,
    onEvent: (AdminOfficesEvent) -> Unit,
) {
    CompactModeLayout(
        officeListState = officeListState,
        onEvent =onEvent,
    )

}