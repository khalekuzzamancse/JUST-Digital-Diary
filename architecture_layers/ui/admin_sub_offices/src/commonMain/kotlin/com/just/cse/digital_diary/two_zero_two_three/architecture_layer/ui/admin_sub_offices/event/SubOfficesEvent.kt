package com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_sub_offices.event

interface SubOfficesEvent {
    data class SubOfficeSelected(val index: Int) : SubOfficesEvent

}