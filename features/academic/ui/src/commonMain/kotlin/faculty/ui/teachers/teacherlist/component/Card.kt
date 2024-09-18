package faculty.ui.teachers.teacherlist.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import common.newui.GenericEmployeeCard


@Composable
internal fun EmployeeCard(
    modifier: Modifier,
    teacher: Teacher,
    expandMode: Boolean = true,
    onCallRequest: () -> Unit,
    onEmailRequest: () -> Unit,
    onMessageRequest: () -> Unit,
) {

    GenericEmployeeCard(
        modifier=modifier,
        name = teacher.name,
        profileImageUrl = null,
        expandMode=expandMode,
        onCallRequest=onCallRequest,
        onEmailRequest = onEmailRequest,
        onMessageRequest = onMessageRequest,
        details = {
            EmployeeDetails(
                teacher = teacher,
                modifier = Modifier
            )
        })


}


@Composable
private fun EmployeeDetails(
    modifier: Modifier,
    teacher: Teacher
) {
    Column(
        modifier = modifier
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = teacher.achievements,
            style = CardTypography.subTitle
        )
        Text(
            text = teacher.designations,
            style = CardTypography.title2
        )
        Text(
            text = teacher.email,
            style = CardTypography.contactStyle
        )
        Text(
            text = teacher.additionalEmail,
            style = CardTypography.contactStyle
        )
        Text(
            text = teacher.phone,
            style = CardTypography.contactStyle
        )


    }
}

internal object  CardTypography{
    val subTitle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        fontFamily = FontFamily.Monospace
    )
    val title2 = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        fontFamily = FontFamily.Default,
    )

    val contactStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        fontFamily = FontFamily.Monospace
    )


}