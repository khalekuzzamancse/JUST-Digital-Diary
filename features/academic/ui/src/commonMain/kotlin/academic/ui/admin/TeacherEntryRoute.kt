package academic.ui.admin

import academic.presentationlogic.controller.admin.TeacherEntryController
import academic.presentationlogic.factory.UiFactory
import academic.presentationlogic.model.public_.Dept
import academic.ui.common.SnackNProgressBarDecorator
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Room
import androidx.compose.material.icons.outlined.School
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import common.ui.CustomTextField
import common.ui.DropDown


/**
 * Composable function that represents the Teacher Form screen with a Done button
 * and sets a maximum width for better appearance on larger screens.
 * - TODO: Need to refactor use viewmodel
 */
@Composable
fun AddTeacherScreen(
    navigationIcon: (@Composable () -> Unit)? = null
) {
    val keyboard = LocalSoftwareKeyboardController.current
    val controller = remember { UiFactory.createTeacherAddForm() }
    val areMandatoryFieldsValid =
        controller.validator.areMandatoryFieldFilled.collectAsState().value
    SnackNProgressBarDecorator(
        isLoading = controller.networkIOInProgress.collectAsState().value,
        snackBarMessage = controller.statusMessage.collectAsState(null).value,
        navigationIcon = navigationIcon
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            _TeacherForm(
                controller = controller,
                modifier = Modifier
                    .widthIn(max = 600.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.widthIn(min = 100.dp, max = 300.dp).fillMaxWidth(),
                enabled = areMandatoryFieldsValid,
                onClick = {
                    keyboard?.hide()
                }
            ) {

                Text("Done")
            }

        }
    }
}


/**
 * Composable function that displays a form to edit a TeacherModel.
 * It utilizes TeacherFormController to manage state and handle events.
 */
@Composable
private fun _TeacherForm(
    modifier: Modifier = Modifier,
    controller: TeacherEntryController,
) {
    val teacher by controller.teacherState.collectAsState()

    Column(modifier = modifier.widthIn(max = 600.dp)) {
        DropDown(
            modifier = modifier,
            options = controller.dept.collectAsState().value.map { "${it.name}(${it.shortName})" },
            selected = controller.selectedDeptIndex.collectAsState().value,
            label = "Select a department",
            leadingIcon = Icons.Outlined.School,
            onOptionSelected = controller::onDeptChange,
        )
        _VerticalSpace()
        CustomTextField(
            label = "Name",
            value = teacher.name,
            onValueChanged = controller::onNameChange,
            leadingIcon = Icons.Outlined.Person
        )
        _VerticalSpace()
        CustomTextField(
            label = "Email",
            value = teacher.email,
            onValueChanged = controller::onEmailChange,
            leadingIcon = Icons.Outlined.Email
        )
        _VerticalSpace()

        CustomTextField(
            label = "Additional Email",
            value = teacher.additionalEmail,
            onValueChanged = controller::onAdditionalEmailChange,
            leadingIcon = Icons.Outlined.Email
        )
        _VerticalSpace()

        CustomTextField(
            label = "Achievements",
            value = teacher.achievements,
            onValueChanged = controller::onAchievementsChange,
            leadingIcon = Icons.Outlined.Description
        )
        _VerticalSpace()

        CustomTextField(
            label = "Phone",
            value = teacher.phone,
            onValueChanged = { newPhone ->
                val filteredPhone = newPhone.filter { it.isDigit() }
                controller.onPhoneChange(filteredPhone)
            },
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Phone,
            leadingIcon = Icons.Outlined.Phone
        )
        _VerticalSpace()

        CustomTextField(
            label = "Designations",
            value = teacher.designations,
            onValueChanged = controller::onDesignationsChange,
            leadingIcon = Icons.Outlined.Description
        )

        _VerticalSpace()
        CustomTextField(
            label = "Room No",
            value = teacher.roomNo,
            onValueChanged = { newRoomNo ->
                controller.onRoomNoChange(newRoomNo)
            },
            keyboardType = KeyboardType.Number,
            leadingIcon = Icons.Outlined.Room
        )
        _VerticalSpace()

        CustomTextField(
            label = "Sorting order",
            value = teacher.id,
            onValueChanged = { id ->
                controller.onIdChange(id.filter { it.isDigit() })
            },
            leadingIcon = Icons.Outlined.Badge
        )
    }
}

@Composable
private fun _VerticalSpace() {
    Spacer(modifier = Modifier.height(16.dp))
}

/**
 * Composable function for selecting the teacher's department from a dropdown menu.
 */
@Composable
private fun _DeptDropdown(
    modifier: Modifier = Modifier,
    departments: List<Dept>,
    selectedDept: Dept,
    leadingIcon: ImageVector? = null,
    onDeptChange: (Dept) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf(0) }


    DropDown(
        modifier = modifier,
        options = departments.map { "${it.name} ( ${it.shortname} )" },
        selected = selected,
        leadingIcon = leadingIcon,
        onOptionSelected = {
            selected = it
            onDeptChange(departments[selected])
            expanded = false
        },
        color = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
        )
    )


}

/**
 * Composable function for entering the teacher's room number.
 */
@Composable
private fun _RoomNoField(roomNo: Int, onRoomNoChange: (Int) -> Unit) {
    TextField(
        value = if (roomNo == 0) "" else roomNo.toString(),
        onValueChange = { newValue ->
            val intValue = newValue.toIntOrNull() ?: 0
            onRoomNoChange(intValue)
        },
        label = { Text("Room No") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
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


