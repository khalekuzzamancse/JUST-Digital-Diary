package academic.ui.admin

import academic.presentationlogic.factory.UiFactory
import academic.ui.core.SnackNProgressBarDecorator
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.ui.InsertButton
import kotlinx.coroutines.launch


@Composable
fun InsertFacultyRoute(
    modifier: Modifier = Modifier,
    navigationIcon: (@Composable () -> Unit)? = null
) {
    val controller = remember { UiFactory.insertFacultyController() }
    val isLoading = controller.isLoading.collectAsState().value
    val isInputValid = controller.validator.areMandatoryFieldFilled.collectAsState().value
    val scope = rememberCoroutineScope()
    SnackNProgressBarDecorator(
        isLoading = controller.isLoading.collectAsState().value,
        snackBarMessage = controller.statusMessage.collectAsState(null).value,
        navigationIcon = navigationIcon
    ) {
        Column(
            modifier = Modifier.align(Alignment.TopCenter).verticalScroll(rememberScrollState())
        ) {
            FacultyEntryForm(
                modifier = Modifier,
                controller = controller,
            )
            Spacer(Modifier.height(32.dp))
            InsertButton(
                modifier = Modifier.widthIn(min = 100.dp, max = 300.dp).fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                enabled = !isLoading && isInputValid
            ) {
                scope.launch {
                    controller.insert()
                }
            }

        }
    }


}


