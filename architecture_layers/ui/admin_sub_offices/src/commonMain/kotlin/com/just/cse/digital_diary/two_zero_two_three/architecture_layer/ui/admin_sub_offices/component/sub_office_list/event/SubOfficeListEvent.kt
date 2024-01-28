package com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_sub_offices.component.sub_office_list.event

import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_sub_offices.destination.event.SubOfficeDestinationEvent

interface SubOfficeListEvent : SubOfficeDestinationEvent {
    data class SubOfficeSelected(val index: Int) : SubOfficeListEvent
    data object DismissRequest: SubOfficeListEvent
}