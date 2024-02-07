package com.just.cse.digital_diary.features.admin_office.components.officers.state

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.admin_officers.component.employee_list.state.AdminOfficerListState

data class AdminOfficerListDestinationState(
    val isLoading: Boolean = false,
    val message: String?=null,
    val adminOfficerListState: AdminOfficerListState=AdminOfficerListState(),
)
