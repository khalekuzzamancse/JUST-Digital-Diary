@file:Suppress("VariableName", "FunctionName")

package calender.interface_adapter

import calender.add_calender.HolidayEditorUiController
import calender.add_calender.Option
import calender.common.CalenderCellUiModel
import calender.common.HolidayUiModel
import di.DIFactory
import domain.model.CalendarModel
import domain.model.DayNameModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

internal class HolidayEditorUiControllerImpl : HolidayEditorUiController {
    private var calender: CalendarModel? = null
    private val _currentMonthCalender = MutableStateFlow<List<CalenderCellUiModel>?>(null)
    override val currentMonthCalender = _currentMonthCalender.asStateFlow()
    private var currentMonthIndex =
        MutableStateFlow(LocalDate.now().month.ordinal) //from jan to dec (0 to 11)

    private val _monthName = MutableStateFlow(LocalDate.now().month.name)
    override val monthName = _monthName.asStateFlow()
    private val _year = MutableStateFlow<Int?>(null)
    override val year = _year.asStateFlow()
    private val _selected = MutableStateFlow<Set<CalenderCellUiModel>>(emptySet())
    override val selected = _selected.asStateFlow()
    private val _showDialog = MutableStateFlow(false)
    override val showDialog = _showDialog.asStateFlow()
    private var _allMonthData: List<List<CalenderCellUiModel>> = emptyList()


    init {

        CoroutineScope(Dispatchers.Default).launch {
            delay(2_000)//pretending loading...
            loadCalender()
            _year.update { calender?.year }
            _updateAllMonthData()
            _updateCalender(currentMonthIndex.value)

        }
        _observeSelectedMonth()

    }

    private fun _observeSelectedMonth() {
        CoroutineScope(Dispatchers.Default).launch {
            currentMonthIndex.collect { requestMonth ->//Jan-Dec ( 0-11)
                if (requestMonth._isValidMonth() && requestMonth._hasThisMonthData()) {
                    _updateCalender(requestMonth)
                    _updateMonth(requestMonth)
                }
            }
        }
    }


    //TODO:Events handler
    override fun onSelectionRequest(cell: CalenderCellUiModel) {
        val alreadySelected = (_selected.value.find { it == cell } != null)
        //remove
        if (alreadySelected) {
            _selected.update { selected -> selected - cell }
        }
        //add
        else {
            _selected.update { selected -> selected + cell }
        }
        println(selected.value.mapNotNull { it.dayOrdinal })
    }

    override fun onHolidayAddRequest() {
        _showDialog.update { true }
    }

    override fun onDialogDismissRequest() {
        _showDialog.update { false }
    }

    override fun onHolidayConfirm(reason: String, type: Option) {
        _showDialog.update { false }
        _updateHoliday(_selected.value.mapNotNull { it.dayOrdinal }, reason, type)
        _selected.update { emptySet() }
    }

    private fun _updateHoliday(dates: List<Int>, reason: String, type: Option) {
        try {
            calender?.let { calender ->
                val currentMonthData: List<CalenderCellUiModel> =
                    _allMonthData[currentMonthIndex.value] //catch exception

                val updatedData = currentMonthData.map { day ->
                    if (dates.contains(day.dayOrdinal)) {
                        day.copy(
                            holiday = HolidayUiModel(
                                colorHexCode = when (type) {
                                    Option.AllOff -> "#FF0000"       // Red color hex code for AllOff
                                    Option.OnlyClassOf -> "#00FF00" // Green color hex code for OnlyClassOff
                                    Option.SpecialDay -> "#800080"   // Purple color hex code for SpecialDay
                                },
                                reason = reason
                            )
                        )
                    } else day

                }

                _currentMonthCalender.update { updatedData }
                val newList = _allMonthData.map { it }.toMutableList()
                newList[currentMonthIndex.value] = updatedData
                _allMonthData = newList

            }
        } catch (e: Exception) {

        }


    }

    override fun goToNextMonthCalender() =
        _updateCalenderCurrentIndexIfValid(currentMonthIndex.value + 1)

    override fun goToPreviousMonthCalender() =
        _updateCalenderCurrentIndexIfValid(currentMonthIndex.value - 1)


    //TODO: state updated method section----------
    //TODO: state updated method section----------

    private fun _updateCalenderCurrentIndexIfValid(requestedMonthOrdinal: Int) {
        if (requestedMonthOrdinal._isValidMonth())
            currentMonthIndex.update { requestedMonthOrdinal }
    }

    private suspend fun loadCalender() {
        DIFactory
            .createRawRetrieveCalenderUseCase()
            .execute(2024, weekend = listOf(DayNameModel.THURSDAY, DayNameModel.FRIDAY))
            .onSuccess {
                calender = it
            }
            .onFailure {
                calender = null
            }
    }
    private fun _updateAllMonthData() {
        calender?.let {
            _allMonthData = CalendarUIGridModelPresenter().buildMonthGrid(
                it.months
            )
        }
    }

    /*Just update, neither throw exception or check validity*/
    private fun _updateCalender(monthOrdinal: Int) {
        _currentMonthCalender.update {
            _allMonthData[monthOrdinal]
        }
    }

    //TODO:Helper method section-----------
    //TODO:Helper method section-----------
    private fun _updateMonth(index: Int)=
        _monthName.update { calender?.months?.get(index)?.month.toString() }
    private fun Int._hasThisMonthData() = (_allMonthData.size > this)
    private fun Int._isValidMonth() = (this in 0..11)
}
