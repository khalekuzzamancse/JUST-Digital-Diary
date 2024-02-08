package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.components.office_list.state

data class OfficeListState(
    val offices: List<Offices> = emptyList(),
    val selected:Int=-1
)
