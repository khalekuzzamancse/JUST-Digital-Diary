@file:Suppress("UnUsed")

package schedule.ui.ui.admin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Label
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Class
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Splitscreen
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.StateFlow
import schedule.ui.ui.ExamScheduleScreen
import schedule.ui.ui.common.CustomTextField
import schedule.ui.ui.common.ErrorText
import schedule.ui.factory.UiFactory
import schedule.ui.model.ExamScheduleModel

/**
 * - Manages the state and handles events related to form inputs
 * - Keeps an abstract `validator`, forcing the implementer to provide a separate implementation
 *   for validation. This ensures single responsibility and separation of concerns, so if any
 *   validation logic needs to change or need separate implementation for validate, there is no need to modify the `Controller`
 * - By defining the `validator` here, it also controls which `fields` should be validated
 * @property onCourseAddRequest Returns success or failure, allowing the form to be closed
 *   or kept open based on the result
 */

interface ExamScheduleFormController {
    val state: StateFlow<ExamScheduleModel>
    val validator: Validator
    val year: StateFlow<String>
    val semester: StateFlow<String>
    val session: StateFlow<String>

    //for a single course
    val courseCode: StateFlow<String>
    val courseTitle: StateFlow<String>
    val time: StateFlow<String>
    val date: StateFlow<String>

    //
    fun onSessionChanged(value: String)
    fun onYearChanged(value: String)
    fun onSemesterChanged(value: String)
    fun onCourseCodeChanged(value: String)
    fun onCourseTitleChanged(value: String)
    fun onTimeChanged(value: String)
    fun onDateChanged(value: String)
    fun onCourseAddRequest(): Boolean
    interface Validator {
        val areAllFieldsFilled: StateFlow<Boolean>
        val errors: StateFlow<List<String>>
        fun observeFieldChanges(
            year: StateFlow<String>,
            semester: StateFlow<String>,
            session: StateFlow<String>,
            //related to course
            courseCode: StateFlow<String>,
            courseTitle: StateFlow<String>,
            time: StateFlow<String>,
            date: StateFlow<String>
        )

    }


}

@Composable
fun ExamScheduleAddScreen(modifier: Modifier = Modifier) {
    val controller: ExamScheduleFormController = remember { UiFactory.createExamScheduleFormController() }
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
        ExamScheduleScreen(schedule)
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
    controller: ExamScheduleFormController,
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
    controller: ExamScheduleFormController,
) {
    Column(modifier = modifier.widthIn(max = 600.dp)) {
        CustomTextField(
            label = "Session",
            value = controller.session.collectAsState().value,
            onValueChanged = controller::onSessionChanged,
            leadingIcon = Icons.Filled.Event,
        )
        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            label = "Year",
            value = controller.year.collectAsState().value,
            onValueChanged = controller::onYearChanged,
            leadingIcon = Icons.Filled.Class
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            label = "Semester",
            value = controller.semester.collectAsState().value,
            onValueChanged = controller::onSemesterChanged,
            leadingIcon = Icons.Filled.Splitscreen
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            label = "Course code",
            value = controller.courseCode.collectAsState().value,
            onValueChanged = controller::onCourseCodeChanged,
            leadingIcon = Icons.AutoMirrored.Filled.Label,
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            label = "Course title",
            value = controller.courseTitle.collectAsState().value,
            onValueChanged = controller::onCourseTitleChanged,
            leadingIcon = Icons.Filled.Description,
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            label = "Date",
            value = controller.date.collectAsState().value,
            onValueChanged = controller::onDateChanged,
            leadingIcon = Icons.Filled.DateRange,
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            label = "Time",
            value = controller.time.collectAsState().value,
            onValueChanged = controller::onTimeChanged,
            leadingIcon = Icons.Filled.AccessTime,
        )
        controller.validator.errors.collectAsState().value.let { error ->
            if (error.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                ErrorText(modifier = Modifier, error)
            }

        }
    }
}