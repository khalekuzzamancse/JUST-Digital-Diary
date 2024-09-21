package academic.ui.admin

import academic.ui.factory.UiFactory
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import common.ui.drop_down.DropDown
import kotlinx.coroutines.flow.StateFlow

data class Dept(
    val name: String,
    val shortname: String
) {
    override fun toString(): String {
        return "$name ( $shortname)"
    }
}

data class TeacherModel(
    val name: String,
    val email: String,
    val additionalEmail: String,
    val achievements: String,
    val phone: String,
    val designations: String,
    val dept: Dept, // Use dropdown
    val roomNo: Int,
)


/**
 * Interface that defines the contract for controlling the TeacherForm.
 * It manages the state of TeacherModel and handles events related to form inputs.
 *
 * @property teacherState A [StateFlow] that emits the current state of [TeacherModel].
 * @property onNameChange Handles the event when the teacher's name changes.
 * @property onEmailChange Handles the event when the teacher's email changes.
 * @property onAdditionalEmailChange Handles the event when the teacher's additional email changes.
 * @property onAchievementsChange Handles the event when the teacher's achievements change.
 * @property onPhoneChange Handles the event when the teacher's phone number changes.
 * @property onDesignationsChange Handles the event when the teacher's designations change.
 * @property onDeptChange Handles the event when the teacher's department changes.
 * @property onRoomNoChange Handles the event when the teacher's room number changes.
 */
interface TeacherFormController {
    val teacherState: StateFlow<TeacherModel>
    fun onNameChange(newName: String)
    fun onEmailChange(newEmail: String)
    fun onAdditionalEmailChange(newAdditionalEmail: String)
    fun onAchievementsChange(newAchievements: String)
    fun onPhoneChange(newPhone: String)
    fun onDesignationsChange(newDesignations: String)
    fun onDeptChange(newDept: Dept)
    fun onRoomNoChange(newRoomNo: Int)
}


/**
 * Composable function that represents the Teacher Form screen with a Done button
 * and sets a maximum width for better appearance on larger screens.
 */
@Composable
fun TeacherFormScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        _TeacherForm(
            modifier = Modifier
                .widthIn(max = 600.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.widthIn(min=100.dp, max=300.dp).fillMaxWidth(),
                onClick = { /* Handle Done action */ }
            ) {

                Text("Done")
            }

    }
}


/**
 * Composable function that displays a form to edit a TeacherModel.
 * It utilizes TeacherFormController to manage state and handle events.
 */
@Composable
private fun _TeacherForm(
    modifier: Modifier = Modifier
) {
    val controller = remember { UiFactory.createTeacherAddForm() }
    val teacher by controller.teacherState.collectAsState()
    Column(modifier = modifier.widthIn(max = 600.dp)) {
        _TextField(
            label = "Name",
            value = teacher.name,
            onValueChanged = controller::onNameChange
        )
        Spacer(modifier = Modifier.height(16.dp))
        _TextField(
            label = "Email",
            value = teacher.email,
            onValueChanged = controller::onEmailChange
        )
        Spacer(modifier = Modifier.height(16.dp))

        _TextField(
            label = "Additional Email",
            value = teacher.additionalEmail,
            onValueChanged = controller::onAdditionalEmailChange
        )
        Spacer(modifier = Modifier.height(16.dp))

        _TextField(
            label = "Achievements",
            value = teacher.achievements,
            onValueChanged = controller::onAchievementsChange
        )
        Spacer(modifier = Modifier.height(16.dp))

        _TextField(
            label = "Phone",
            value = teacher.phone,
            onValueChanged = { newPhone ->
                val filteredPhone = newPhone.filter { it.isDigit() }
                controller.onPhoneChange(filteredPhone)
            },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )
        Spacer(modifier = Modifier.height(16.dp))

        _TextField(
            label = "Designations",
            value = teacher.designations,
            onValueChanged = controller::onDesignationsChange
        )
        Spacer(modifier = Modifier.height(16.dp))

        _DeptDropdown(
            modifier = Modifier.fillMaxWidth(),
            selectedDept = teacher.dept,
        ) { newDept ->
            controller.onDeptChange(newDept)
        }
        Spacer(modifier = Modifier.height(16.dp))

        _TextField(
            label = "Room No",
            value = if (teacher.roomNo == 0) "" else teacher.roomNo.toString(),
            onValueChanged = { newRoomNo ->
                val intValue = newRoomNo.toIntOrNull() ?: 0
                controller.onRoomNoChange(intValue)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}


/**
 * Composable function for selecting the teacher's department from a dropdown menu.
 */
@Composable
private fun _DeptDropdown(
    modifier: Modifier = Modifier,
    selectedDept: Dept,
    onDeptChange: (Dept) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf(0) }
    val departments = listOf(
        Dept("Computer Science", "CS"),
        Dept("Mathematics", "Math"),
        Dept("Physics", "Phys"),
        Dept("Chemistry", "Chem"),
    )

    DropDown(
        modifier = modifier,
        options = departments.map { "${it.name} ( ${it.shortname} )" },
        selected = selected,
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


