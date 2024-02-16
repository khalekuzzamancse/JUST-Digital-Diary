package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.components.home

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.components.home.calender.CalenderFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeDestinationState {
    private val _calenderBuilder =
        MutableStateFlow(CalenderFactory.getCurrentMonthCalenderBuilder())
    val calenderBuilder = _calenderBuilder.asStateFlow()
    val currentYear = CalenderFactory.currentYear
    val monthName: String
        get() = _calenderBuilder.value.monthName.name


    fun goToNextMonth() {
        CalenderFactory
            .getNextMonthCalenderBuilder(_calenderBuilder.value.monthName)
            ?.updateBuilder()
    }

    fun getPreviousMonth() {
        CalenderFactory
            .getPreviousMonthCalenderBuilder(_calenderBuilder.value.monthName)
            ?.updateBuilder()
    }

    private fun CalenderFactory.MonthCalenderBuilder.updateBuilder() {
        _calenderBuilder.update { this }
    }
}