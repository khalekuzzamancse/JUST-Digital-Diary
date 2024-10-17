package academic.ui.admin

import academic.presentationlogic.factory.UiFactory
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import common.ui.SnackNProgressBarDecorator
import common.ui.UpdateButton
import common.ui.VerticalSpace_32
import kotlinx.coroutines.launch


/**
 * Composable function that represents the Teacher Form screen with a Done button
 * and sets a maximum width for better appearance on larger screens.
 * - TODO: Need to refactor use viewmodel
 */
@Composable
internal fun UpdateTeacherRoute(
    teacherId:String,
    navigationIcon: (@Composable () -> Unit)? = null
) {

    val controller = remember { UiFactory.updateTeacherController(teacherId) }
    val isLoading = controller.isLoading.collectAsState().value
    val isInputValid = controller.validator.areMandatoryFieldFilled.collectAsState().value

    val scope = rememberCoroutineScope()
    SnackNProgressBarDecorator(
        isLoading = controller.isLoading.collectAsState().value,
        message = controller.statusMessage.collectAsState(null).value,
        navigationIcon = navigationIcon
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TeacherEntryForm(
                controller = controller,
                modifier = Modifier
                    .widthIn(max = 600.dp)
            )
            VerticalSpace_32()

            UpdateButton(
                modifier = Modifier.widthIn(min = 100.dp, max = 300.dp).fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                enabled = !isLoading && isInputValid
            ) {
                scope.launch {
                    controller.update()
                }
            }


        }
    }
}

