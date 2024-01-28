package com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_sub_offices.destination.state

import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_sub_offices.component.sub_office_list.state.SubOfficeListState

data class SubOfficeListDestinationState(
    val isLoading: Boolean = false,
    val errorMessage: String?=null,
    val subOfficeListState: SubOfficeListState = SubOfficeListState()
)
