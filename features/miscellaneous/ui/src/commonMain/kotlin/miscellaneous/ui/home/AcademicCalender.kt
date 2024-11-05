package miscellaneous.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import calendar.presentationlogic.factory.UIFactory

import calendar.ui.public_.AcademicCalenderView


@Composable
internal fun AcademicCalender(
    modifier: Modifier = Modifier,
    onSnackBarMsgRequest: (reason: String) -> Unit,
) {
    val controller = remember { UIFactory.createCalenderController() }
    //From academic calender module
    AcademicCalenderView(
        modifier = modifier,
        controller = controller,
        onSnackBarMsgRequest = onSnackBarMsgRequest
    )

}