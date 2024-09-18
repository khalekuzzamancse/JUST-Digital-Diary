package miscellaneous.ui.home.home

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import miscellaneous.ui.home.calender.CalenderFactory

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
interface HomeDestinationEvent {
    data class CalenderViewRequest(val url:String) : HomeDestinationEvent
    data object NavigationRequest : HomeDestinationEvent

}