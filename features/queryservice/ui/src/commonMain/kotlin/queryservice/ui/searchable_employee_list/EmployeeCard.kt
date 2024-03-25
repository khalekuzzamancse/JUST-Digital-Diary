package queryservice.ui.searchable_employee_list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
    Surface(
        modifier = modifier,
        shadowElevation = 2.dp
    ) {
        Column(
            modifier = modifier
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.Start
        ) {
//            ImageLoader(
//                url = employee.profileImageLink,
//                modifier = Modifier
//                    .heightIn(max = 100.dp)
//                    .widthIn(max = 150.dp)
//                    .background(Color.Red)
//                    .align(Alignment.CenterHorizontally),
//            )
            Box(
                modifier = Modifier
                    .height( 100.dp)
                    .width(150.dp)
                    .border(width = 1.dp, color = MaterialTheme.colorScheme.primary)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .align(Alignment.CenterHorizontally),
            )
            ExpandAbleInfo(
                employee = employee,
                highLightedText = highLightedText,
                onCallRequest=onCallRequest,
                onMessageRequest = onMessageRequest,
                onEmailRequest = onEmailRequest

            )

        }


    }

}

@Composable
private fun ExpandAbleInfo(
    modifier: Modifier = Modifier,
    employee: Employee,
    highLightedText: String,
    onCallRequest: () -> Unit,
    onEmailRequest: () -> Unit,
    onMessageRequest: () -> Unit,
) {

    Column(
        modifier = modifier
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            EmployeeName(
                name = employee.name,
                modifier = Modifier,
                highLightedText = highLightedText
            )
        }
        EmployeeDetails(
            modifier = Modifier,
            employee = employee,
            highLightedText=highLightedText
        )
        Controls(
            onCallRequest=onCallRequest,
            onMessageRequest = onMessageRequest,
            onEmailRequest = onEmailRequest
        )

    }

}

@Composable
private fun EmployeeName(
    modifier: Modifier,
    name: String,
    highLightedText: String,
) {
    Text(
        modifier = modifier,
        text = SearcherHighlightedText().getHighLightedString(name, highLightedText),
        style = CardTypography.title
    )

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

