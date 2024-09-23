package administration.ui.admin.add_officers

import administration.factory.UiFactory
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Room
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import common.newui.CustomTextField


/**
 * Composable function that represents the Teacher Form screen with a Done button
 * and sets a maximum width for better appearance on larger screens.
 * - TODO: Need to refactor use viewmodel
 */
@Composable
fun OfficerFormScreen() {
    val controller = remember { UiFactory.createOfficersAddFormController() }
    val areMandatoryFieldsValid=controller.validator.areMandatoryFieldFilled.collectAsState().value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        _Form(
            controller = controller,
            modifier = Modifier
                .widthIn(max = 600.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.widthIn(min=100.dp, max=300.dp).fillMaxWidth(),
                enabled =areMandatoryFieldsValid,
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
private fun _Form(
    modifier: Modifier = Modifier,
    controller: OfficerFormController,
) {
    val model by controller.state.collectAsState()
    Column(modifier = modifier.widthIn(max = 600.dp)) {
        CustomTextField(
            label = "Name",
            value = model.name,
            onValueChanged = controller::onNameChange,
            leadingIcon = Icons.Default.Person
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            label = "Email",
            value = model.email,
            onValueChanged = controller::onEmailChange,
            leadingIcon = Icons.Default.Email
        )
        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            label = "Additional Email",
            value = model.additionalEmail,
            onValueChanged = controller::onAdditionalEmailChange,
            leadingIcon = Icons.Default.Email
        )
        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            label = "Achievements",
            value = model.achievements,
            onValueChanged = controller::onAchievementsChange,
            leadingIcon = Icons.Default.Description
        )
        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            label = "Phone",
            value = model.phone,
            onValueChanged = { newPhone ->
                val filteredPhone = newPhone.filter { it.isDigit() }
                controller.onPhoneChange(filteredPhone)
            },
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Phone,
            leadingIcon = Icons.Default.Phone
        )
        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            label = "Designations",
            value = model.designations,
            onValueChanged = controller::onDesignationsChange,
            leadingIcon = Icons.Default.Description
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            label = "Room No",
            value =model.roomNo ,
            onValueChanged = { newRoomNo ->
                val intValue = newRoomNo.toIntOrNull() ?: 0
                controller.onRoomNoChange(intValue)
            },
           keyboardType = KeyboardType.Number,
            leadingIcon = Icons.Default.Room
        )
    }
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


