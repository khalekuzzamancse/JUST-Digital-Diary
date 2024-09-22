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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import schedule.ui.ClassSchedule
import schedule.ui.model.ClassDetailModel
import schedule.ui.model.ClassScheduleModel
import schedule.ui.model.DailyClassScheduleModel


class ClassScheduleInputFormControllerImpl : ClassScheduleInputFormController {
    private val _state = MutableStateFlow(toEmpty())

    override val state = _state.asStateFlow()

    override fun onSessionChanged(value: String) {
        _state.update {
            it.copy(session = value)
        }
    }

    override fun onYearChanged(value: String) {
        _state.update {
            it.copy(year = value)
        }
    }

    override fun onSemesterChanged(value: String) {
        _state.update {
            it.copy(semester = value)
        }
    }

    override fun onCourseAddRequest(day: String, value: ClassDetailModel) {
        _state.update { schedule ->
            val dailyClassScheduleModel = schedule.routine.find { it.day == day }
            if (dailyClassScheduleModel != null) {
                val classesOnThisDay: List<ClassDetailModel> = dailyClassScheduleModel.items
                schedule.copy(
                    routine = schedule.routine.map {
                        if (it.day == day)
                            it.copy(items = classesOnThisDay + value)//adding to existing
                        else
                            it
                    }
                )
            } else
                schedule.copy(
                    routine = listOf(DailyClassScheduleModel(day, listOf(value)))//newly adding
                )

        }
        println(_state.value)
    }

    private fun toEmpty() = ClassScheduleModel(
        dept = "",
        session = "",
        year = "",
        semester = "",
        routine = emptyList()
    )

}


/**
 * Interface that defines the contract for controlling the TeacherForm.
 * It manages the state of TeacherModel and handles events related to form inputs.
 */
interface ClassScheduleInputFormController {
    val state: StateFlow<ClassScheduleModel>
    fun onSessionChanged(value: String)
    fun onYearChanged(value: String)
    fun onSemesterChanged(value: String)
    fun onCourseAddRequest(day: String, value: ClassDetailModel)
}

@Composable
fun InputForm(modifier: Modifier = Modifier) {
    val controller = remember { ClassScheduleInputFormControllerImpl() }
    var showDialog by remember { mutableStateOf(false) }
    val schedule=controller.state.collectAsState().value
    Column {
        Button(
            onClick = {
                showDialog=true
            }
        ){
            Text("Add")
        }
        ClassSchedule(schedule)
    }

    if (showDialog){
        _ReasonDialog(
            controller = controller,
            onDismiss = {
                showDialog=false
            },
            onDone = {
                showDialog=false
            }
        )
    }


}

@Composable
private fun _ReasonDialog(
    controller: ClassScheduleInputFormController,
    onDismiss: () -> Unit,
    onDone: () -> Unit
) {
    val days = remember { listOf("Sat", "Sun", "Mon", "Tue", "Wed") }
    var courseCode = rememberSaveable { mutableStateOf("") }
    var selectedDayIndex = rememberSaveable { mutableStateOf(0) }
    var teacherName = rememberSaveable { mutableStateOf("") }
    var startTime = rememberSaveable { mutableStateOf("") }
    var endTime = rememberSaveable { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        text = {
            _InputForm(
                modifier = Modifier.verticalScroll(rememberScrollState()).horizontalScroll(
                    rememberScrollState()
                ),
                controller = controller,
                days = days,
                courseCode = courseCode,
                selectedDayIndex = selectedDayIndex,
                teacherName = teacherName,
                startTime = startTime,
                endTime = endTime,
            )

        },
        confirmButton = {
            TextButton(
                onClick = {
                    onDone()
                    controller.onCourseAddRequest(
                        day = days[selectedDayIndex.value],
                        value = ClassDetailModel(
                            courseCode = courseCode.value,
                            time = "${startTime.value}-${endTime.value}",
                            teacherShortName = teacherName.value,
                            durationInHours = 1//TODO
                        )
                    )
                    //clear them all
                    courseCode.value = "";startTime.value = "";endTime.value =
                    "";teacherName.value = ""
                },
                enabled = true
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
    controller: ClassScheduleInputFormController,
    days: List<String>,
    courseCode: MutableState<String>,
    selectedDayIndex: MutableState<Int>,
    teacherName: MutableState<String>,
    startTime: MutableState<String>,
    endTime: MutableState<String>
) {

    val validator = remember { InputValidator() }
    Column(modifier = modifier.widthIn(max = 600.dp)) {
        _TextField(
            label = "Session",
            value = controller.state.collectAsState().value.session,
            onValueChanged = { input ->
                controller.onSessionChanged(input.filter { it.isDigit() || it == '-' })

            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        _TextField(
            label = "Year",
            value = controller.state.collectAsState().value.year,
            onValueChanged = { input ->
                if (validator.validateYear(input))
                    controller.onYearChanged(input)
                else
                    controller.onYearChanged("")
            }
        )
        Spacer(modifier = Modifier.width(16.dp))
        _TextField(
            label = "Semester",
            value = controller.state.collectAsState().value.semester,
            onValueChanged = { input ->
                if (validator.validateSemester(input))
                    controller.onSemesterChanged(input)
                else
                    controller.onSemesterChanged("")
            }
        )

        _TextField(
            label = "Course code",
            value = courseCode.value,
            onValueChanged = { courseCode.value = it }
        )
        Spacer(modifier = Modifier.height(16.dp))
        _TextField(
            label = "Teacher short name",
            value = teacherName.value,
            onValueChanged = { teacherName.value = it }
        )
        Spacer(modifier = Modifier.height(16.dp))
        _TextField(
            label = "Start time",
            value = startTime.value,
            onValueChanged = { input ->
                if (validator.validateTime(input))
                    startTime.value = input
                else
                    startTime.value = ""

            }
        )
        Spacer(modifier = Modifier.width(16.dp))
        _TextField(
            label = "End time",
            value = endTime.value,
            onValueChanged = { input ->
                if (validator.validateTime(input))
                    endTime.value = input
                else
                    endTime.value = ""
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        _DayOptions(
            options = days,
            selected = selectedDayIndex.value,
            onOptionSelected = {
                selectedDayIndex.value = it
            }
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
    onValueChanged: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    val colors = TextFieldDefaults.colors(
        focusedContainerColor = MaterialTheme.colorScheme.surface,
        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
    )
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChanged,
        label = {
            Text(label)
        },
        colors = colors,
        keyboardOptions = keyboardOptions
    )
}

