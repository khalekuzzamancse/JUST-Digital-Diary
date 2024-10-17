package academic.ui.admin
import academic.presentationlogic.controller.admin.TeacherEntryController
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.InsertLink
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Room
import androidx.compose.material.icons.outlined.School
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import common.ui.CustomTextField
import common.ui.DropDown
import common.ui.VerticalSpace_16

/**
 * Composable function that displays a form to edit a TeacherModel.
 * It utilizes TeacherFormController to manage state and handle events.
 */
@Composable
internal fun TeacherEntryForm(
    modifier: Modifier = Modifier,
    controller: TeacherEntryController,
) {
    val teacher by controller.teacherState.collectAsState()

    Column(modifier = modifier.widthIn(max = 600.dp)) {
        DropDown(
            modifier = modifier,
            options = controller.dept.collectAsState().value.map { "${it.name}(${it.shortname})" },
            selected = controller.selectedDeptIndex.collectAsState().value,
            label = "Select a department",
            leadingIcon = Icons.Outlined.School,
            onOptionSelected = controller::onDeptChange,
        )
       VerticalSpace_16()

        CustomTextField(
            label = "Name",
            value = teacher.name,
            onValueChanged = controller::onNameChange,
            leadingIcon = Icons.Outlined.Person
        )
        VerticalSpace_16()

        CustomTextField(
            label = "Email",
            value = teacher.email,
            onValueChanged = controller::onEmailChange,
            leadingIcon = Icons.Outlined.Email
        )

        VerticalSpace_16()

        CustomTextField(
            label = "Additional Email",
            value = teacher.additionalEmail?:"",
            onValueChanged = controller::onAdditionalEmailChange,
            leadingIcon = Icons.Outlined.Email
        )

        VerticalSpace_16()

        CustomTextField(
            label = "Achievements",
            value = teacher.achievements,
            onValueChanged = controller::onAchievementsChange,
            leadingIcon = Icons.Outlined.Description
        )

        VerticalSpace_16()

        CustomTextField(
            label = "Phone",
            value = teacher.phone,
            onValueChanged = { newPhone ->
                val filteredPhone = newPhone.filter { it.isDigit()||it==',' }
                controller.onPhoneChange(filteredPhone)
            },
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Phone,
            leadingIcon = Icons.Outlined.Phone
        )

        VerticalSpace_16()

        CustomTextField(
            label = "Designations",
            value = teacher.designations,
            onValueChanged = controller::onDesignationsChange,
            leadingIcon = Icons.Outlined.Description
        )

        VerticalSpace_16()

        CustomTextField(
            label = "Room No",
            value = teacher.roomNo?:"",
            onValueChanged = { newRoomNo ->
                controller.onRoomNoChange(newRoomNo)
            },
            keyboardType = KeyboardType.Number,
            leadingIcon = Icons.Outlined.Room
        )

        VerticalSpace_16()

        CustomTextField(
            label = "Priority",
            value = teacher.priority,
            onValueChanged = { id ->
                controller.onIdChange(id.filter { it.isDigit() })
            },
            leadingIcon = Icons.Outlined.Badge
        )

        VerticalSpace_16()

        CustomTextField(
            label = "Image Link",
            value = teacher.profileImageLink?:"",
            onValueChanged = controller::onImageLinkChange,
            leadingIcon = Icons.Outlined.InsertLink
        )
    }
}
@Composable
private fun _VerticalSpace() {
    Spacer(modifier = Modifier.height(16.dp))
}