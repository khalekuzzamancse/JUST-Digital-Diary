package academic.ui.admin

import academic.presentationlogic.factory.UiFactory
import academic.ui.common.SnackNProgressBarDecorator
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Update
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun UpdateDeptRoute(
    modifier: Modifier = Modifier,
    navigationIcon: (@Composable () -> Unit)? = null
) {

    val controller = remember { UiFactory.updateDeptController("climateanddisastermanagement") }
    val keyboard = LocalSoftwareKeyboardController.current
    val isLoading = controller.networkIOInProgress.collectAsState().value
    val isInputValid = controller.validator.areMandatoryFieldFilled.collectAsState().value
    val scope = rememberCoroutineScope()

    SnackNProgressBarDecorator(
        isLoading = controller.networkIOInProgress.collectAsState().value,
        snackBarMessage = controller.statusMessage.collectAsState(null).value,
        navigationIcon = navigationIcon
    ) {
        Column(
            modifier = Modifier.align(Alignment.TopCenter).verticalScroll(rememberScrollState())) {
            DeptEntryForm(
                modifier = Modifier,
                controller = controller,
            )
            Spacer(Modifier.height(32.dp))
            Button(
                modifier = Modifier.widthIn(min = 100.dp, max = 300.dp).fillMaxWidth().align(Alignment.CenterHorizontally),
                enabled = !isLoading && isInputValid,
                onClick = {
                    keyboard?.hide()
                    scope.launch {
                        controller.update()
                    }
                }
            ) {
                Icon(Icons.Outlined.Update, contentDescription = "add")
                Spacer(Modifier.width(6.dp))
                Text("Update")
            }
        }


    }

}
