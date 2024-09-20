package miscellaneous.ui.home.calender

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import calender.factory.UIFactory

@Composable
internal fun CalenderUI(
    modifier: Modifier = Modifier,
    onSnackBarMsgRequest: (reason: String) -> Unit,
) {
    val controller = remember { UIFactory.createCalenderController() }
    //From academic calender module
    calender.ui.calender.AcademicCalenderUI(
        modifier = Modifier,
        controller = controller,
        onSnackBarMsgRequest = onSnackBarMsgRequest
    )

}