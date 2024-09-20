package calender.add_calender

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import calender.common.AcademicCalender
import calender.common.CalenderCellUiModel
import calender.common.LoadingUI
import calender.factory.UIFactory
import calender.ui.calender.AcademicCalenderUI
import kotlinx.coroutines.flow.StateFlow

/**
 * - Manage the state and event of the [AcademicCalenderUI]
 * - ViewModel can implement it(optional)
 * @property monthName name of the month that calender is currently showing
 * @property goToNextMonthCalender Update the[currentMonthCalender] with next month
 * @property goToPreviousMonthCalender  Update the [currentMonthCalender] with previous  month
 */
interface HolidayEditorUiController {
    val currentMonthCalender: StateFlow<List<CalenderCellUiModel>?>
    val monthName: StateFlow<String>
    val year: StateFlow<Int?>
    val selected: StateFlow<Set<CalenderCellUiModel>>

    fun onSelectionRequest(cell: CalenderCellUiModel)
    fun onHolidayConfirm(reason: String, type: HolidayTypeUiModel)
    fun goToNextMonthCalender()
    fun goToPreviousMonthCalender()
}


/**
 * @param onSnackBarMsgRequest parent should show the reason via snackBar
 */
@Composable
fun HolidayEditor(
    modifier: Modifier = Modifier,
    controller: HolidayEditorUiController = remember { UIFactory.createCalenderEditorController() },
    onSnackBarMsgRequest: (reason: String) -> Unit,
) {
    /**
     * - Controller should be UI independent (as much as possible)
     * - In future we may use different UI for input instead of dialog, that is is why not keeping `show dialog`
     * in the controller
     */
    var showDialog by rememberSaveable { mutableStateOf(false) }

    val cellData =
        controller.currentMonthCalender.collectAsState().value   // Calender Grid cell data

    if (showDialog) {
        ParentComposable(
            onDismiss = {
                showDialog = false
            },
            onDone = { reason, type ->
                showDialog = false
                controller.onHolidayConfirm(reason, type)
            }
        )
    }

    if (cellData == null) {
        LoadingUI()
    } else {
        _Scaffold(
            enableAddButton = controller.selected.collectAsState().value.isNotEmpty(),
            onAddButtonClick = {
                showDialog=true
            }
        ) {
            AcademicCalender(
                year = controller.year.collectAsState("").value.toString(),
                monthName = controller.monthName.collectAsState().value,
                onNext = controller::goToNextMonthCalender,
                onPrev = controller::goToPreviousMonthCalender,
                cellUiModels = cellData,
                onHolidayClick = onSnackBarMsgRequest,
                //Using as Editor
                selected = controller.selected.collectAsState().value,
                onClick = controller::onSelectionRequest
            )
        }

    }
}

@Composable
private fun _Scaffold(
    enableAddButton: Boolean,
    onAddButtonClick: () -> Unit,
    content: @Composable (Modifier) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            Button(
                onClick = onAddButtonClick,
                enabled = enableAddButton
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add")
                Spacer(Modifier.width(4.dp))
                Text("Add")
            }
        }
    ) { innerPadding ->
        content(Modifier.padding(innerPadding))
    }
}
