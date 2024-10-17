package academic.ui.admin

import academic.presentationlogic.controller.admin.DeptEntryController
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Sort
import androidx.compose.material.icons.outlined.School
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.ui.CustomTextField
import common.ui.DropDown
import common.ui.VerticalSpace_8

/**
 * Will be used for both add and update
 */
@Composable
internal fun DeptEntryForm(
    modifier: Modifier = Modifier,
    controller: DeptEntryController,
) {
    Column(
        modifier = modifier
            .widthIn(max = 600.dp)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        DropDown(
            modifier = modifier,
            options = controller.faculties.collectAsState().value.map { it.name },
            selected = controller.selectedFacultyIndex.collectAsState().value,
            label = "Select a faculty",
            leadingIcon = Icons.Outlined.School,
            onOptionSelected = controller::onFacultySelected,
        )
        VerticalSpace_8()

        CustomTextField(
            label = "Department Name",
            value = controller.dept.collectAsState().value.name,
            onValueChanged = controller::onNameChanged,
            leadingIcon = Icons.Outlined.School
        )
        VerticalSpace_8()

        CustomTextField(
            label = "Short name",
            value = controller.dept.collectAsState().value.shortname,
            onValueChanged = controller::onShortNameChanged,
            leadingIcon = Icons.Outlined.School
        )

        VerticalSpace_8()

        CustomTextField(
            label = "Priority",
            value = controller.dept.collectAsState().value.priority,
            onValueChanged = controller::onPriorityChanged,
            leadingIcon = Icons.AutoMirrored.Outlined.Sort
        )


    }

}

