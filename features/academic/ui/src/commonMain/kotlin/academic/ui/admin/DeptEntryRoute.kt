package academic.ui.admin

import academic.presentationlogic.controller.admin.DepartmentEntryController
import academic.presentationlogic.factory.UiFactory
import academic.presentationlogic.model.admin.DepartmentEntryModel
import academic.ui.common.SnackNProgressBarDecorator
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Sort
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.Update
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import common.ui.CustomTextField
import common.ui.DropDown

@Composable
fun UpdateDeptRoute(
    modifier: Modifier = Modifier,
    navigationIcon: (@Composable () -> Unit)? = null
) {
    val model = DepartmentEntryModel("cse", "Computer Science and Engineering", "CSE", "")
    val controller = remember { UiFactory.createDepartEntryController() }
    LaunchedEffect(Unit) {
        controller.onPriorityChanged(model.priority)
        controller.onNameChanged(model.name)
        controller.onShortNameChanged(model.shortName)
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
fun AddDeptRoute(
    modifier: Modifier = Modifier,
    navigationIcon: (@Composable () -> Unit)? = null
) {
    val controller = remember { UiFactory.createDepartEntryController() }
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
    controller: DepartmentEntryController,
    buttonContent: @Composable () -> Unit
) {
    val keyboard = LocalSoftwareKeyboardController.current
    val isLoading = controller.networkIOInProgress.collectAsState().value
    val isInputValid = controller.validator.areMandatoryFieldFilled.collectAsState().value
    Column(
        modifier = modifier
            .widthIn(max = 600.dp)
            .padding(16.dp),
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
        Spacer(Modifier.height(8.dp))
        CustomTextField(
            label = "Department Name",
            value = controller.dept.collectAsState().value.name,
            onValueChanged = controller::onNameChanged,
            leadingIcon = Icons.Outlined.School
        )
        Spacer(Modifier.height(8.dp))
        CustomTextField(
            label = "Short name",
            value = controller.dept.collectAsState().value.shortName,
            onValueChanged = controller::onShortNameChanged,
            leadingIcon = Icons.Outlined.School
        )

        Spacer(Modifier.height(8.dp))
        CustomTextField(
            label = "Priority",
            value = controller.dept.collectAsState().value.priority,
            onValueChanged = controller::onPriorityChanged,
            leadingIcon = Icons.AutoMirrored.Outlined.Sort
        )

        Spacer(Modifier.height(32.dp))
        Button(
            modifier = Modifier.widthIn(min = 100.dp, max = 300.dp).fillMaxWidth(),
            enabled = !isLoading && isInputValid,
            onClick = {
                keyboard?.hide()
            }
        ) {
            buttonContent()
        }

    }

}

