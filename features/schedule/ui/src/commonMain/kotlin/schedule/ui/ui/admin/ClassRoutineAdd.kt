package schedule.ui.ui.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Label
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Class
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Splitscreen
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.StateFlow
import schedule.ui.ui.common.ErrorText
import schedule.ui.ui.common.CustomTextField
import schedule.ui.factory.Factory
import schedule.ui.model.ClassScheduleModel
import schedule.ui.ui.ClassSchedule


/**
 * - Manages the state and handles events related to form inputs
 * - Keeps an abstract `validator`, forcing the implementer to provide a separate implementation
 *   for validation. This ensures single responsibility and separation of concerns, so if any
 *   validation logic needs to change or need separate implementation for validate, there is no need to modify the `Controller`
 * - By defining the `validator` here, it also controls which `fields` should be validated
 * @property onCourseAddRequest Returns success or failure, allowing the form to be closed
 *   or kept open based on the result
 */

interface ClassScheduleFormController {
    val days: List<String>
        get() = listOf("Sat", "Sun", "Mon", "Tue", "Wed")
    val state: StateFlow<ClassScheduleModel>
    val validator: Validator
    val courseCode: StateFlow<String>
    val selectedDayIndex: StateFlow<Int>
    val teacherName: StateFlow<String>
    val startTime: StateFlow<String>
    val endTime: StateFlow<String>
    val year: StateFlow<String>
    val semester: StateFlow<String>
    val session: StateFlow<String>
    fun onSessionChanged(value: String)
    fun onYearChanged(value: String)
    fun onSemesterChanged(value: String)
    fun onCourseAddRequest(): Boolean
    fun onCourseCodeChanged(value: String)
    fun onSelectedDayIndexChanged(value: Int)
    fun onTeacherNameChanged(value: String)
    fun onStartTimeChanged(value: String)
    fun onEndTimeChanged(value: String)

    interface Validator {
        val areAllFieldsFilled: StateFlow<Boolean>
        val errors: StateFlow<List<String>>
        fun observeFieldChanges(
            courseCodeFlow: StateFlow<String>,
            yearFlow: StateFlow<String>,
            semesterFlow: StateFlow<String>,
            sessionFlow: StateFlow<String>,
            teacherNameFlow: StateFlow<String>,
            startTimeFlow: StateFlow<String>,
            endTimeFlow: StateFlow<String>
        )

    }


}

@Composable
fun InputForm(modifier: Modifier = Modifier) {
    val controller = remember { Factory.createClassScheduleFormController() }
    var showDialog by remember { mutableStateOf(false) }
    val schedule = controller.state.collectAsState().value
    Column {
        Button(
            onClick = {
                showDialog = true
            }
        ) {
            Text("Add")
        }
        ClassSchedule(schedule)
    }

    if (showDialog) {
        _ReasonDialog(
            controller = controller,
            onDismiss = {
                showDialog = false
            },
            onDone = {
                showDialog = false
            }
        )
    }


}

@Composable
private fun _ReasonDialog(
    controller: ClassScheduleFormController,
    onDismiss: () -> Unit,
    onDone: () -> Unit
) {
    val noError = controller.validator.errors.collectAsState().value.isEmpty()
    val allFieldsFilled = controller.validator.areAllFieldsFilled.collectAsState().value

    AlertDialog(
        onDismissRequest = onDismiss,
        text = {
            _InputForm(
                modifier = Modifier
                    .verticalScroll(rememberScrollState()),
                controller = controller,
            )

        },
        confirmButton = {
            TextButton(
                onClick = {
                    val isAdded = controller.onCourseAddRequest()
                    if (isAdded)
                        onDone()

                },
                enabled = noError && allFieldsFilled
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

@Composable
private fun _InputForm(
    modifier: Modifier = Modifier,
    controller: ClassScheduleFormController,
) {
    Column(modifier = modifier.widthIn(max = 600.dp)) {
        _TextField(
            label = "Session",
            value = controller.session.collectAsState().value,
            onValueChanged = controller::onSessionChanged,
            leadingIcon = Icons.Filled.Event,
        )
        Spacer(modifier = Modifier.height(16.dp))

        _TextField(
            label = "Year",
            value = controller.year.collectAsState().value,
            onValueChanged = controller::onYearChanged,
            leadingIcon = Icons.Filled.Class
        )
        Spacer(modifier = Modifier.height(16.dp))
        _TextField(
            label = "Semester",
            value = controller.semester.collectAsState().value,
            onValueChanged = controller::onSemesterChanged,
            leadingIcon = Icons.Filled.Splitscreen
        )
        Spacer(modifier = Modifier.height(16.dp))
        _TextField(
            label = "Course code",
            value = controller.courseCode.collectAsState().value,
            onValueChanged = controller::onCourseCodeChanged,
            leadingIcon = Icons.AutoMirrored.Filled.Label,
        )
        Spacer(modifier = Modifier.height(16.dp))
        _TextField(
            label = "Teacher short name",
            value = controller.teacherName.collectAsState().value,
            onValueChanged = controller::onTeacherNameChanged,
            leadingIcon = Icons.Filled.Person,
        )
        Spacer(modifier = Modifier.height(16.dp))
        _TextField(
            label = "Start time",
            value = controller.startTime.collectAsState().value,
            onValueChanged = controller::onStartTimeChanged,
            leadingIcon = Icons.Filled.AccessTime,
        )
        Spacer(modifier = Modifier.height(16.dp))
        _TextField(
            label = "End time",
            value = controller.endTime.collectAsState().value,
            onValueChanged = controller::onEndTimeChanged,
            leadingIcon = Icons.Filled.AccessTime,
        )
        controller.validator.errors.collectAsState().value.let { error ->
            if (error.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                ErrorText(modifier = Modifier, error)
            }

        }

        Spacer(modifier = Modifier.height(16.dp))
        _DayOptions(
            options = controller.days,
            selected = controller.selectedDayIndex.collectAsState().value,
            onOptionSelected = controller::onSelectedDayIndexChanged
        )


    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun _DayOptions(
    options: List<String>,
    selected: Int,
    onOptionSelected: (Int) -> Unit
) {

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
    ) {
        options.forEachIndexed { index, option ->
            _RadioButton(
                label = option,
                isSelected = selected == index,
                onClick = { onOptionSelected(index) }
            )
        }

    }


}

//TODO:Helper method
@Composable
private fun _RadioButton(
    modifier: Modifier = Modifier,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 4.dp)
    ) {
        RadioButton(
            selected = isSelected,
            onClick = onClick
        )
        Text(text = label, modifier = Modifier.padding(start = 8.dp))
    }

}

@Composable
private fun _TextField(
    modifier: Modifier = Modifier.fillMaxWidth(),
    label: String,
    value: String,
    leadingIcon: ImageVector,
    onValueChanged: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    CustomTextField(
        modifier = modifier,
        label = label,
        value = value,
        leadingIcon = leadingIcon,
        onValueChanged = onValueChanged
    )


}

