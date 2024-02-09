package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.components.layout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.components.office_list.OfficeList
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.components.office_list.state.OfficeListState
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.event.AdminOfficesEvent


@Composable
internal fun CompactModeLayout(
    modifier: Modifier=Modifier,
    officeListState: OfficeListState,
    onEvent: (AdminOfficesEvent) -> Unit,
) {
    OfficeList(
        modifier = modifier,
        onEvent = onEvent,
        state = officeListState
    )


}

