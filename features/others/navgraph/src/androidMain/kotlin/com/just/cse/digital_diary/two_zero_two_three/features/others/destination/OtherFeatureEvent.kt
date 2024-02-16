package com.just.cse.digital_diary.two_zero_two_three.features.others.destination
/**
 * It is a separate to prevent public low level event,that should not be care about the client module
 */
interface OtherFeatureEvent {
    data class CalenderRequest(val url:String):OtherFeatureEvent
}