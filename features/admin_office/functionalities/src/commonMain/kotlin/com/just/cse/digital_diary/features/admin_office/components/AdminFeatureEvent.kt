package com.just.cse.digital_diary.features.admin_office.components

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.event.AdminOfficesEvent

/**
 * Used as convert or separate between the [AdminOfficesEvent] so that client module does not need to depends
 * on the lower module
 */
interface AdminFeatureEvent {
    data class CallRequest(val number: String) : AdminFeatureEvent
    data class MessageRequest(val number: String) : AdminFeatureEvent
    data class EmailRequest(val email: String) : AdminFeatureEvent
}