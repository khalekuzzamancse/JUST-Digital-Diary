package miscellaneous.ui.home.calender

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import calendar.ui.factory.UIFactory
import calendar.ui.ui.public_.AcademicCalenderView


@Composable
internal fun CalenderUI(
    modifier: Modifier = Modifier,
    onSnackBarMsgRequest: (reason: String) -> Unit,
) {
    val controller = remember { UIFactory.createCalenderController() }
    //From academic calender module
    AcademicCalenderView(
        modifier = Modifier,
        controller = controller,
        onSnackBarMsgRequest = onSnackBarMsgRequest
    )

}