@file:Suppress("UnUsed")

package schedule.ui.ui.admin.add_exam_schedule

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.newui.CustomTextField
import schedule.ui.ui.public_.ExamScheduleScreen
import schedule.ui.ui.common.ErrorText
import schedule.ui.factory.UiFactory
import schedule.ui.ui.common.InputDialog
import schedule.ui.ui.common.ScaffoldDecorator


@Composable
fun ExamScheduleAddScreen(modifier: Modifier = Modifier) {
    val controller: ExamScheduleFormController =
        remember { UiFactory.createExamScheduleFormController() }
    var showForm by remember { mutableStateOf(false) }
    val schedule = controller.state.collectAsState().value
    val noError = controller.validator.errors.collectAsState().value.isEmpty()
    val allFieldsFilled = controller.validator.areAllFieldsFilled.collectAsState().value
    ScaffoldDecorator(
        onAddButtonClick = {
            showForm = true
        }
    ){
        ExamScheduleScreen(examSchedule = schedule)
    }
    if (showForm) {
        InputDialog(
            onDismiss = {
                showForm = false
            },
            enabledDone = noError && allFieldsFilled,
            onDone = {
                val isAdded = controller.onCourseAddRequest()
                if (isAdded)
                    showForm = false
            }
        ) {
            _Form(
                modifier = Modifier
                    .verticalScroll(rememberScrollState()),
                controller = controller,
            )
        }

    }


}

@Composable
private fun _Form(
    modifier: Modifier = Modifier,
    controller: ExamScheduleFormController,
) {
    Column(modifier = modifier.widthIn(max = 600.dp)) {
        CustomTextField(
            label = "Session",
            value = controller.session.collectAsState().value,
            onValueChange = controller::onSessionChanged,
            leadingIcon = Icons.Filled.Event,
        )
        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            label = "Year",
            value = controller.year.collectAsState().value,
            onValueChange = controller::onYearChanged,
            leadingIcon = Icons.Filled.Class
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            label = "Semester",
            value = controller.semester.collectAsState().value,
            onValueChange = controller::onSemesterChanged,
            leadingIcon = Icons.Filled.Splitscreen
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            label = "Course code",
            value = controller.courseCode.collectAsState().value,
            onValueChange = controller::onCourseCodeChanged,
            leadingIcon = Icons.AutoMirrored.Filled.Label,
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            label = "Course title",
            value = controller.courseTitle.collectAsState().value,
            onValueChange = controller::onCourseTitleChanged,
            leadingIcon = Icons.Filled.Description,
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            label = "Date",
            value = controller.date.collectAsState().value,
            onValueChange = controller::onDateChanged,
            leadingIcon = Icons.Filled.DateRange,
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            label = "Time",
            value = controller.time.collectAsState().value,
            onValueChange = controller::onTimeChanged,
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