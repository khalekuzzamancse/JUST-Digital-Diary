@file:Suppress("VariableName", "FunctionName")

package calender.interface_adapter

import calender.ui.calender.CalendarController
import calender.ui.calender.CalenderCellUiModel
import di.DIFactory
import domain.model.CalendarModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate

internal class CalenderControllerImpl: CalendarController {
    private var calender: CalendarModel? = null
    private val _currentMonthCalender = MutableStateFlow<List<CalenderCellUiModel>?>(null)
    override val currentMonthCalender = _currentMonthCalender.asStateFlow()
    private var currentMonthIndex = LocalDate.now().month.ordinal//from jan to dec (0 to 11)
    private val _monthName = MutableStateFlow(LocalDate.now().month.name)
    override val monthName = _monthName.asStateFlow()
    private val _year = MutableStateFlow<Int?>(null)
    override val year = _year.asStateFlow()


    init {
        loadCalender()
        updateCalender(currentMonthIndex)
    }

    override fun goToNextMonthCalender() {
        val nextMonthOrdinal = currentMonthIndex + 1
        if (nextMonthOrdinal._isValidMonth()) {
            updateCalender(nextMonthOrdinal)
            currentMonthIndex = nextMonthOrdinal
        }


    }

    override fun goToPreviousMonthCalender() {
        val prevMonthOrdinal = currentMonthIndex - 1
        if (prevMonthOrdinal._isValidMonth()) {
            updateCalender(prevMonthOrdinal)
            currentMonthIndex = prevMonthOrdinal
        }

    }


    private fun loadCalender() {
        DIFactory
            .createRetrieveCalenderUseCase()
            .execute(2024)
            .onSuccess {
                calender = it
            }
            .onFailure {
                calender = null
            }
    }

    private fun updateCalender(monthOrdinal: Int) {
        calender?.let { calender ->
            val month = calender.months[monthOrdinal]
            _monthName.update { month.month.name }
            _currentMonthCalender.update {
                CalendarUIGridModelPresenter().buildMonthGrid(month)
            }
            _year.update { calender.year }
        }

    }

    private fun Int._isValidMonth() = (this in 0..11)
}
