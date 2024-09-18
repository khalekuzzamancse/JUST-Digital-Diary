package queryservice.ui.searchable_employee_list

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
import common.ui.search_bar.SearcherHighlightedText


@Composable
internal fun SearchedEmployeeCard(
    modifier: Modifier,
    employee: Employee,
    highLightedText: String = "",
    onCallRequest: () -> Unit,
    onEmailRequest: () -> Unit,
    onMessageRequest: () -> Unit,
) {
    GenericEmployeeCard(
        modifier=modifier,
        name = employee.name,
        profileImageUrl = null,
        expandMode=false,
        onCallRequest=onCallRequest,
        onEmailRequest = onEmailRequest,
        onMessageRequest = onMessageRequest,
        details = {
            EmployeeDetails(
                modifier = Modifier,
                employee = employee,
                highLightedText=highLightedText
            )
        })


}

@Composable
private fun EmployeeDetails(
    modifier: Modifier,
    employee: Employee,
    highLightedText: String,
) {
    Column(
        modifier = modifier
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = SearcherHighlightedText().getHighLightedString(employee.achievements,highLightedText),
            style = CardTypography.subTitle
        )
        Text(
            text = SearcherHighlightedText().getHighLightedString(employee.designations,highLightedText),
            style = CardTypography.title2
        )
        Text(
            text = SearcherHighlightedText().getHighLightedString(employee.email,highLightedText),
            style = CardTypography.contactStyle
        )
        Text(
            text =SearcherHighlightedText().getHighLightedString(employee.additionalEmail,highLightedText),
            style = CardTypography.contactStyle
        )
        Text(
            text =SearcherHighlightedText().getHighLightedString(employee.phone,highLightedText),
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