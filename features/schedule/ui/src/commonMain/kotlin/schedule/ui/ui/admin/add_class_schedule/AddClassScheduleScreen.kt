package schedule.ui.ui.admin.add_class_schedule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Label
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Class
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Splitscreen
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.ui.CustomTextField
import schedule.ui.factory.UiFactory
import schedule.ui.ui.common.ErrorText
import schedule.ui.ui.common.InputDialog
import schedule.ui.ui.common.ScaffoldDecorator
import schedule.ui.ui.public_.ClassSchedule


@Composable
fun AddClassScheduleScreen(modifier: Modifier = Modifier) {
    val controller = remember { UiFactory.createClassScheduleFormController() }
    var showForm by remember { mutableStateOf(false) }
    val schedule = controller.state.collectAsState().value
    val noError = controller.validator.errors.collectAsState().value.isEmpty()
    val allFieldsFilled = controller.validator.areAllFieldsFilled.collectAsState().value
    ScaffoldDecorator(
        onAddButtonClick = {
            showForm = true
        }
    ){
        ClassSchedule(schedule)
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
    controller: ClassScheduleFormController,
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
            label = "Teacher short name",
            value = controller.teacherName.collectAsState().value,
            onValueChanged = controller::onTeacherNameChanged,
            leadingIcon = Icons.Filled.Person,
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            label = "Start time",
            value = controller.startTime.collectAsState().value,
            onValueChanged = controller::onStartTimeChanged,
            leadingIcon = Icons.Filled.AccessTime,
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
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



