package academic.ui.admin

import academic.presentationlogic.controller.admin.FacultyEntryController
import academic.presentationlogic.controller.admin.InsertFacultyController
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.School
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import common.ui.CustomTextField

/**
 * Will be used for both add and update
 */
@Composable
internal fun FacultyEntryForm(
    modifier: Modifier = Modifier,
    controller: FacultyEntryController,
) {

    Column(
        modifier = modifier
            .widthIn(max = 600.dp)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier.height(8.dp))
        CustomTextField(
            label = "Faculty Name",
            value = controller.faculty.collectAsState().value.name,
            onValueChanged = controller::onNameChanged,
            leadingIcon = Icons.Outlined.School
        )
        Spacer(Modifier.height(8.dp))
        CustomTextField(
            label = "Priority",
            value = controller.faculty.collectAsState().value.priority,
            onValueChanged = controller::onPriorityChanged,
            leadingIcon = Icons.Outlined.Badge
        )

    }

}