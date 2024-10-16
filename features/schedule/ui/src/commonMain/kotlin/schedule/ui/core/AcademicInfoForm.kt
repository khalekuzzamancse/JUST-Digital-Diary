package schedule.ui.core

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Class
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Splitscreen
import androidx.compose.material.icons.outlined.School
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.ui.CustomTextField
import common.ui.DropDown
import schedule.presentationlogic.controller.core.AcademicInfoController


@Composable
internal fun AcademicInfoForm(
    modifier: Modifier = Modifier,
    controller: AcademicInfoController,
    onDone: () -> Unit,
) {
    Column(
        modifier = modifier.widthIn(max = 600.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DropDown(
            modifier = Modifier,
            options = controller.department.collectAsState().value.map { "${it.name}(${it.shortname})" },
            selected = controller.selectedDeptIndex.collectAsState().value,
            label = "Select a department",
            leadingIcon = Icons.Outlined.School,
            onOptionSelected = controller::onDeptSelected,
        )
        Spacer(modifier = Modifier.height(16.dp))
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

        controller.validator.errors.collectAsState().value.let { error ->
            if (error.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                ErrorText(modifier = Modifier, error)
            }

        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = onDone,
            enabled = true,
        ) {
            Text("Done")
        }

    }
}
