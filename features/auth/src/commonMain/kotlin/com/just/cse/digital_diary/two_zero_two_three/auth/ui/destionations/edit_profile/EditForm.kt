package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.edit_profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Person4
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.common.AuthDropDownMenu
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.common.AuthTextField
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.registration.RegistraionViewModel

@Composable
internal fun EditForm(
    modifier: Modifier = Modifier,
    fieldModifier: Modifier = Modifier,
    viewModel: EditProfileViewModel,
) {
    val data = viewModel.data.collectAsState().value
    EditForm(
        modifier = modifier,
        fieldModifier = fieldModifier.fillMaxWidth(),
        data = data,
        onNameChanged = viewModel::onFullNameChanged,
        onUserNameChanged = viewModel::onUsernameChanged,
        onDeptChanged = viewModel::onDeptChanged,
    )

}
data class EditFormData(
    val name: String,
    val dept:String,
    val username: String,
)

object EditFormLabels {
    const val FULL_NAME = "Full Name"
    const val USER_NAME = "User Name"
    const val Dept = "Department"
}

@Composable
fun EditForm(
    modifier: Modifier = Modifier,
    fieldModifier: Modifier,
    data: EditFormData,
    onNameChanged: (String) -> Unit,
    onUserNameChanged: (String) -> Unit,
    onDeptChanged: (String) -> Unit,
) {

    RegistrationForm(
        modifier = modifier,
        fieldModifier = fieldModifier,
        name = data.name,
        onNameChanged = onNameChanged,
        username = data.username,
        onUserNameChanged = onUserNameChanged,
        departments = listOf("CSE", "EEE"),
        dept = data.dept,
        onDeptChanged = onDeptChanged,
    )

}

@Composable
private fun RegistrationForm(
    modifier: Modifier = Modifier,
    fieldModifier: Modifier,
    name: String,
    onNameChanged: (String) -> Unit,
    username: String,
    onUserNameChanged: (String) -> Unit,
    departments: List<String>,
    dept: String,
    onDeptChanged: (String) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
            AuthTextField(
                modifier = fieldModifier,
                label = EditFormLabels.FULL_NAME,
                value = name,
                onValueChanged = onNameChanged,
                keyboardType = KeyboardType.Text,
                leadingIcon = Icons.Default.Person,
            )

           AuthTextField(
               modifier = fieldModifier,
               label = EditFormLabels.USER_NAME,
               value = username,
               onValueChanged = onUserNameChanged,
               keyboardType = KeyboardType.Text,
               leadingIcon = Icons.Default.Person4,
           )

           AuthDropDownMenu(
               modifier = fieldModifier,
               label = EditFormLabels.Dept,
               options = departments,
               onOptionSelected = onDeptChanged,
               selected = dept
           )



    }

}





