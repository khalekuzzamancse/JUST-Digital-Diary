package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.components.layout.CompactModeLayout
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.event.AdminOfficesEvent
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.components.office_list.state.OfficeListState

@Composable
fun AdminOfficeList(
    modifier: Modifier=Modifier,
    officeListState: OfficeListState,
    onEvent: (AdminOfficesEvent) -> Unit,
) {
    CompactModeLayout(
        modifier=modifier,
        officeListState = officeListState,
        onEvent =onEvent,
    )

}