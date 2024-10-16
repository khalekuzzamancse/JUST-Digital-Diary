package schedule.ui.admin

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Label
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.ui.CustomTextField
import common.ui.SnackNProgressBarDecorator
import kotlinx.coroutines.launch
import schedule.presentationlogic.controller.ClassScheduleController
import schedule.presentationlogic.factory.UiFactory
import schedule.ui.core.AcademicInfoForm
import schedule.ui.core.ErrorText
import schedule.ui.core.InputDialog
import schedule.ui.public_.ClassSchedule


@Composable
fun AddClassScheduleScreen(modifier: Modifier = Modifier) {
    val controller = remember { UiFactory.createClassScheduleFormController() }
    var showScheduleAddFrom by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val showAcademicForm = !(controller.isAcademicDetailsFilled.collectAsState().value)

    SnackNProgressBarDecorator(
        isLoading = controller.isLoading.collectAsState().value,
        snackBarMessage = controller.statusMessage.collectAsState(null).value,
        fab = {
            _Actions(
                enable = !showAcademicForm,
                onAddClassClick = {
                    showScheduleAddFrom = true
                },
                onUploadRequest = {
                    scope.launch { controller.insert() }
                }
            )
        }
    ) {
        if (showAcademicForm) {
            //Not using dialog  because dialog not able to show the snack bar message
            AcademicInfoForm(
                modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
                controller = controller.academicController,
                onDone = controller::onAcademicInfoAddRequest
            )
        } else {
            _ScheduleAdd(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .horizontalScroll(rememberScrollState()),
                controller = controller,
                showScheduleAddFrom = showScheduleAddFrom,
                onDone = {
                    val isAdded = controller.addToSchedule()
                    if (isAdded)
                        showScheduleAddFrom = false
                },
                onDismiss = {
                    showScheduleAddFrom = false
                }
            )
        }


    }


}

@Composable
private fun _Actions(
    enable: Boolean,
    onUploadRequest: () -> Unit,
    onAddClassClick: () -> Unit
) {
    Row {
        Button(
            enabled = enable,
            onClick = onUploadRequest
        ) {
            Icon(imageVector = Icons.Default.Upload, contentDescription = "submit")
        }
        Spacer(Modifier.width(16.dp))
        IconButton(
            enabled = enable,
            onClick = onAddClassClick,
        ) {
            Icon(imageVector = Icons.Default.AddCircle, contentDescription = "add class")
        }
    }
}

@Composable
private fun _ScheduleAdd(
    modifier: Modifier = Modifier,
    controller: ClassScheduleController,
    showScheduleAddFrom: Boolean,
    onDone: () -> Unit,
    onDismiss: () -> Unit,
) {
    val schedule = controller.schedule.collectAsState().value
    val noError = controller.validator.errors.collectAsState().value.isEmpty()
    val allFieldsFilled = controller.validator.areAllFieldsFilled.collectAsState().value
    ClassSchedule(schedule = schedule, modifier = modifier.padding(8.dp))

    if (showScheduleAddFrom) {
        InputDialog(
            onDismiss = onDismiss,
            enabledDone = noError && allFieldsFilled,
            onDone = onDone
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
    controller: ClassScheduleController,
) {
    Column(modifier = modifier.widthIn(max = 600.dp)) {
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



