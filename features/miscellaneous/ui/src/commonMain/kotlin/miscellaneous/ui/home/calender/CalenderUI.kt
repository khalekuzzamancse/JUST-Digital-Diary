package miscellaneous.ui.home.calender

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import calendar.ui.factory.UIFactory
import calendar.ui.viewer.AcademicCalenderViewer

@Composable
internal fun CalenderUI(
    modifier: Modifier = Modifier,
    onSnackBarMsgRequest: (reason: String) -> Unit,
) {
    val controller = remember { UIFactory.createCalenderController() }
    //From academic calender module
    AcademicCalenderViewer(
        modifier = Modifier,
        controller = controller,
        onSnackBarMsgRequest = onSnackBarMsgRequest
    )

}