package calendar.ui.admin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import calendar.presentationlogic.factory.UIFactory
import calendar.presentationlogic.model.HolidayType
import calendar.ui.admin.add_calender.CalenderEntryViewModel
import calendar.ui.component.AcademicCalender
import calendar.ui.component.ProgressBarDecorator
import calendar.ui.component.SnackNProgressBarDecorator
import common.ui.InsertButton
import common.ui.UpdateButton
import kotlinx.coroutines.launch

@Composable
fun InsertAcademicCalenderRoute(modifier: Modifier = Modifier) {
    val viewModel = remember {
        CalenderEntryViewModel(
            editor = UIFactory.calenderEditorController(),
            controller = UIFactory.entryController()
        )
    }
    val scope = rememberCoroutineScope()
    SnackNProgressBarDecorator(
        isLoading = viewModel.controller.isLoading.collectAsState().value,
        snackBarMessage = viewModel.controller.statusMessage.collectAsState().value,
    ) {
        _HolidayEditorUI(
            controller = viewModel.editor,
            onSnackBarMsgRequest = {},
            onInsertRequest = {
                scope.launch {
                    viewModel.insert()
                }

            },
            onUpdateRequest = {
                scope.launch {
                    viewModel.update()
                }
            }
        )
    }


}

/**
 * - It show a calender that is editable
 * - Editable means it dates can be selected as assign a holiday
 * - It has `+` add to to trigger the event of adding holiday to the selected dates
 * - It handle a input `dialog` that takes the reason and the `type` of holiday
 * - It uses the [AcademicCalender] as component
 * @param controller should pass the controller via `di`, in case of `manual di` pass instance should be
 * takes from `factory method`
 * @param onSnackBarMsgRequest parent should show the reason via snackBar
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun _HolidayEditorUI(
    controller: HolidayEditorController,
    onSnackBarMsgRequest: (reason: String) -> Unit,
    onInsertRequest: () -> Unit,
    onUpdateRequest: () -> Unit,
) {
    /**
     * - Controller should be UI independent (as much as possible)
     * - In future we may use different UI for input instead of dialog, that is is why not keeping `show dialog`
     * in the controller
     */
    var showDialog by rememberSaveable { mutableStateOf(false) }

    val cellData =
        controller.currentMonthData.collectAsState().value   // Calender Grid cell data

    if (showDialog) {
        _HolidayInputDialog(
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
        ProgressBarDecorator()
    } else {
        _Scaffold(
            modifier = Modifier.fillMaxWidth(),
            enableAddButton = controller.selectedDates.collectAsState().value.isNotEmpty(),
            onAddButtonClick = {
                showDialog = true
            }
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AcademicCalender(
                    modifier = Modifier,
                    year = controller.currentYear.collectAsState("").value.toString(),
                    monthName = cellData.name,
                    onNext = controller::goToNextMonthCalender,
                    onPrev = controller::goToPreviousMonthCalender,
                    cellUiModels = cellData.cells,
                    onHolidayClick = onSnackBarMsgRequest,
                    //Using as Editor
                    selected = controller.selectedDates.collectAsState().value,
                    onClick = controller::onSelectionRequest
                )
                Spacer(Modifier.height(32.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    InsertButton(
                        modifier = Modifier,
                        onClick = onInsertRequest
                    )
                    UpdateButton(
                        modifier = Modifier,
                        onClick = onUpdateRequest
                    )
                }


            }

        }

    }
}

/**
 * - Contain a `+` button to trigger the event of adding selected dates as holiday
 * @param enableAddButton should enable when some dates is selected(non empty)
 */

@Composable
private fun _Scaffold(
    modifier: Modifier = Modifier,
    enableAddButton: Boolean,
    onAddButtonClick: () -> Unit,
    content: @Composable (Modifier) -> Unit
) {
    Scaffold(
        modifier = Modifier,
        floatingActionButton = {
            Button(
                onClick = onAddButtonClick,
                enabled = enableAddButton
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add")
                Spacer(Modifier.width(4.dp))
                Text("Add Holiday")
            }
        }
    ) { innerPadding ->
        Box(modifier, contentAlignment = Alignment.Center) {
            content(Modifier.padding(innerPadding))
        }

    }
}

//TODO: Input dialog section
// Example usage in a parent composable
@Composable
private fun _HolidayInputDialog(
    onDismiss: () -> Unit,
    onDone: (reason: String, selectedOption: HolidayType) -> Unit
) {

    _ReasonDialog(
        onDismiss = onDismiss,
        onDone = onDone
    )

}


// The main dialog composable function
@Composable
private fun _ReasonDialog(
    onDismiss: () -> Unit,
    onDone: (reason: String, selectedOption: HolidayType) -> Unit
) {
    // State variables for reason and selected option
    var reason by remember { mutableStateOf("") }
    var selectedOption by remember { mutableStateOf<HolidayType?>(null) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Enter Reason") },
        text = {
            Column {
                _ReasonTextField(reason) { reason = it }
                Spacer(modifier = Modifier.height(16.dp))
                _OptionRadioButtons(selectedOption) { selectedOption = it }
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onDone(reason, selectedOption!!)
                },
                enabled = reason.isNotEmpty() && selectedOption != null
            ) {
                Text("Done")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

// Composable function for the reason input field
@Composable
private fun _ReasonTextField(reason: String, onReasonChange: (String) -> Unit) {
    OutlinedTextField(
        value = reason,
        onValueChange = onReasonChange,
        label = { Text("Reason") },
        modifier = Modifier.fillMaxWidth()
    )
}

// Composable function for the radio buttons
@Composable
private fun _OptionRadioButtons(
    selectedOption: HolidayType?,
    onOptionSelected: (HolidayType) -> Unit
) {
    Column {
        HolidayType.entries.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onOptionSelected(option) }
                    .padding(vertical = 4.dp)
            ) {
                RadioButton(
                    selected = selectedOption == option,
                    onClick = { onOptionSelected(option) }
                )
                Text(text = option.name, modifier = Modifier.padding(start = 8.dp))
            }
        }
    }
}
