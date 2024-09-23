package miscellaneous.ui.home.home

import Dashboard
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import justdigitaldiary.features.miscellaneous.ui.generated.resources.Res
import justdigitaldiary.features.miscellaneous.ui.generated.resources.just_logo_trans
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

/**
 * * Needed refactor
 * * Since right now unable to read image as shared source,that is why delegating
 * the platform to load the image from their own resource and the logo composable.
 * fix it later using "Res" class  of compose multiplatform resource
 */
@OptIn(ExperimentalResourceApi::class, ExperimentalLayoutApi::class)
@Composable
fun HomeHeader(
    modifier: Modifier = Modifier,
    onEvent:(HomeDestinationEvent)->Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        val res: DrawableResource = Res.drawable.just_logo_trans
        //after that compile it again to generate Res class, use : .\gradlew generateComposeResClass
        Image(
            modifier=Modifier.size(50.dp),
            painter = painterResource(res),//org.jetbrains.compose.resources.
            contentDescription = null,
        )
        AppNameLogoSection(modifier = Modifier, varsityLogoSize = 100.dp)
        Spacer(Modifier.height(8.dp))
        Dashboard(
            isAdmin = true,
            onFacultyListClick = { onEvent(HomeDestinationEvent.NavigateToFacultyList)},
            onAdminOfficerListClick = { onEvent(HomeDestinationEvent.NavigateTAdminOfficeList)},
            onCalendarUpdateClick = { onEvent(HomeDestinationEvent.NavigateToCalendarUpdate)},
            onExamRoutineUpdateClick = { onEvent(HomeDestinationEvent.NavigateToExamRoutineUpdate)},
            onClassRoutineUpdateClick = { onEvent(HomeDestinationEvent.NavigateToClassRoutineUpdate)},
            onTeacherInfoUpdateClick = { onEvent(HomeDestinationEvent.NavigateToTeacherInfoUpdate)},
        )



    }


}

@Composable
private fun _FacultyListButton(
    modifier: Modifier = Modifier,
    onClick:()->Unit,
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ){
        Icon(
            imageVector = Icons.Default.School,
            contentDescription = "faculty list",
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}
@Composable
private fun _AdminOfficeListButton(
    modifier: Modifier = Modifier,
    onClick:()->Unit,
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ){
        Icon(
            imageVector = Icons.Default.AdminPanelSettings,
            contentDescription = "faculty list",
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}



@Composable
private fun AppNameLogoSection(
    modifier: Modifier = Modifier,
    varsityLogoSize:Dp=50.dp
) {

    val text = buildAnnotatedString {
        append(
            AnnotatedString(
                text = "J",
                spanStyle = SpanStyle(MaterialTheme.colorScheme.primary)
            )
        )
        append(AnnotatedString(text = "U", spanStyle = SpanStyle(MaterialTheme.colorScheme.secondary)))
        append(AnnotatedString(text = "S", spanStyle = SpanStyle(MaterialTheme.colorScheme.primary)))
        append(
            AnnotatedString(
                text = "T",
                spanStyle = SpanStyle(MaterialTheme.colorScheme.secondary)
            )
        )
        append(
            AnnotatedString(
                text = " Digital",
                spanStyle = SpanStyle(MaterialTheme.colorScheme.secondary)
            )
        )
        append(
            AnnotatedString(
                text = " Diary",
                spanStyle = SpanStyle(MaterialTheme.colorScheme.primary)
            )
        )
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontWeight = FontWeight.ExtraBold,
                fontSize = 16.sp,
                fontFamily = FontFamily.Cursive
            )
        )

    }

}