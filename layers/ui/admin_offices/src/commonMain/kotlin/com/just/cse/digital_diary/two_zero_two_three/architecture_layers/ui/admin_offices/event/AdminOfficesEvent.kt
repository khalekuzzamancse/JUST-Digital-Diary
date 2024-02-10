package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.event


interface AdminOfficesEvent {
    data class AdminOfficesSelected(val index: Int) : AdminOfficesEvent


}