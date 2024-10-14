package academic.ui.admin

import academic.presentationlogic.factory.UiFactory
import academic.ui.common.SnackNProgressBarDecorator
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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


/**
 * Composable function that represents the Teacher Form screen with a Done button
 * and sets a maximum width for better appearance on larger screens.
 * - TODO: Need to refactor use viewmodel
 */
@Composable
fun UpdateTeacherRoute(
    navigationIcon: (@Composable () -> Unit)? = null
) {
    val keyboard = LocalSoftwareKeyboardController.current
    val controller = remember { UiFactory.updateTeacherController("Prof. Dr. Syed Md. Galib_galib.cse@just.edu.bd") }
    val areMandatoryFieldsValid =
        controller.validator.areMandatoryFieldFilled.collectAsState().value

    val scope = rememberCoroutineScope()
    SnackNProgressBarDecorator(
        isLoading = controller.isLoading.collectAsState().value,
        snackBarMessage = controller.statusMessage.collectAsState(null).value,
        navigationIcon = navigationIcon
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TeacherEntryForm(
                controller = controller,
                modifier = Modifier
                    .widthIn(max = 600.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.widthIn(min = 100.dp, max = 300.dp).fillMaxWidth(),
                enabled = areMandatoryFieldsValid,
                onClick = {
                    keyboard?.hide()
                    scope.launch {
                        controller.update()
                    }

                }
            ) {
                Text("Update")
            }

        }
    }
}

