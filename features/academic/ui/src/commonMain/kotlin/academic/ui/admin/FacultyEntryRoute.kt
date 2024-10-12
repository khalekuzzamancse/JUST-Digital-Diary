package academic.ui.admin

import academic.presentationlogic.controller.admin.FacultyEntryController
import academic.presentationlogic.factory.UiFactory
import academic.presentationlogic.model.admin.FacultyEntryModel
import academic.ui.common.SnackNProgressBarDecorator
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.Update
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import common.ui.CustomTextField
import kotlinx.coroutines.launch

@Composable
fun UpdateFacultyRoute(
    modifier: Modifier = Modifier,
    navigationIcon: (@Composable () -> Unit)? = null
) {
    val model = FacultyEntryModel("FET", "Faculty of Engineering and Technology")
    val controller = remember { UiFactory.createAddFacultyController() }
    LaunchedEffect(Unit) {
        controller.onPriorityChanged(model.priority)
        controller.onNameChanged(model.name)
    }
    SnackNProgressBarDecorator(
        isLoading = controller.networkIOInProgress.collectAsState().value,
        snackBarMessage = controller.statusMessage.collectAsState(null).value,
        navigationIcon = navigationIcon
    ) {
        _FormAndControl(
            modifier = Modifier.align(Alignment.TopCenter),
            controller = controller,
            buttonContent = {
                Icon(Icons.Outlined.Update, contentDescription = "add")
                Spacer(Modifier.width(6.dp))
                Text("Update")
            }
        )

    }

}

@Composable
fun AddFacultyRoute(
    modifier: Modifier = Modifier,
    navigationIcon: (@Composable () -> Unit)? = null
) {
    val controller = remember { UiFactory.createAddFacultyController() }
    SnackNProgressBarDecorator(
        isLoading = controller.networkIOInProgress.collectAsState().value,
        snackBarMessage = controller.statusMessage.collectAsState(null).value,
        navigationIcon = navigationIcon
    ) {
        _FormAndControl(
            modifier = Modifier.align(Alignment.TopCenter),
            controller = controller,
            buttonContent = {
                Icon(Icons.Outlined.Add, contentDescription = "add")
                Spacer(Modifier.width(6.dp))
                Text("Add")
            }
        )

    }

}


/**
 * Will be used for both add and update
 */
@Composable
private fun _FormAndControl(
    modifier: Modifier = Modifier,
    controller: FacultyEntryController,
    buttonContent: @Composable () -> Unit
) {
    val keyboard = LocalSoftwareKeyboardController.current
    val isLoading = controller.networkIOInProgress.collectAsState().value
    val isInputValid = controller.validator.areMandatoryFieldFilled.collectAsState().value
    val scope = rememberCoroutineScope()
    Column(
        modifier = modifier
            .widthIn(max = 600.dp)
            .padding(16.dp),
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
        Spacer(Modifier.height(32.dp))
        Button(
            modifier = Modifier.widthIn(min = 100.dp, max = 300.dp).fillMaxWidth(),
            enabled = !isLoading && isInputValid,
            onClick = {
                keyboard?.hide()
                scope.launch {
                    controller.onAddRequest()
                }
            }
        ) {
            buttonContent()
        }

    }

}