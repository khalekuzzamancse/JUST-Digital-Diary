package com.just.cse.digital_diary.features.admin_office.components.destination

import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_sub_offices.component.sub_office_list.state.SubOfficeListState
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.components.office_list.state.OfficeListState

data class AdminOfficesDestinationState(
    val isLoading: Boolean = false,
    val message: String? = null,
    val officeState: OfficeListState = OfficeListState(),
    val subOfficeState:SubOfficeListState?=null,
    val showSubOfficeList: Boolean = false,
)
