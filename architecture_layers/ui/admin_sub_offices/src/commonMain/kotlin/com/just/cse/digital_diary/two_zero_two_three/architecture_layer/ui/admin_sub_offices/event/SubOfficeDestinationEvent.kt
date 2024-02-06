package com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_sub_offices.event

interface SubOfficeDestinationEvent {
    data object ExitRequest: SubOfficeDestinationEvent
    interface SubOfficeListEvent : SubOfficeDestinationEvent {
        data class SubOfficeSelected(val index: Int) : SubOfficeListEvent
    }

}