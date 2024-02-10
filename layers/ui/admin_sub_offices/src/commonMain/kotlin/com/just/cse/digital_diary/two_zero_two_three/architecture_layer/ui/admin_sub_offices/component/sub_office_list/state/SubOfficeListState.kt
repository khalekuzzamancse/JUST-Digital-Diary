package com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_sub_offices.component.sub_office_list.state

data class SubOfficeListState(
    val title: String? = null,
    val enableBackNavigation: Boolean = true,
    val subOffices: List<SubOffice> = emptyList(),
    val selected: Int = -1
)