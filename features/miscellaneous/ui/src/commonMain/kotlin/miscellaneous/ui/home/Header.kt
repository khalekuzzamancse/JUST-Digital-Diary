package miscellaneous.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AdminPanelSettings
import androidx.compose.material.icons.outlined.School
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import justdigitaldiary.features.miscellaneous.ui.generated.resources.Res
import justdigitaldiary.features.miscellaneous.ui.generated.resources.just_logo_trans
import miscellaneous.MiscFeatureEvent
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
    onEvent: (MiscFeatureEvent) -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        val res: DrawableResource = Res.drawable.just_logo_trans
        //after that compile it again to generate Res class, use : .\gradlew generateComposeResClass
        Image(
            modifier = Modifier.size(50.dp)
                .background(Color.White),//will work for both dark and light
            painter = painterResource(res),//org.jetbrains.compose.resources.
            contentDescription = "JUST",
        )
        FlowRow {
            _FacultyListButton(
                onClick ={
                    onEvent(MiscFeatureEvent.NavigateToFacultyList)
                }
            )
            Spacer(Modifier.width(4.dp))
            AppNameLogoSection(modifier = Modifier)
            Spacer(Modifier.width(4.dp))
            _AdminOfficeListButton(
                onClick ={
                    onEvent(MiscFeatureEvent.NavigateTAdminOfficeList)
                }
            )
        }


    }


}

@Composable
private fun _FacultyListButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.Outlined.School,
            contentDescription = "faculty list",
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
private fun _AdminOfficeListButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.Outlined.AdminPanelSettings,
            contentDescription = "faculty list",
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}


@Composable
private fun AppNameLogoSection(
    modifier: Modifier = Modifier
) {

    val text = buildAnnotatedString {
        append(
            AnnotatedString(
                text = "J",
                spanStyle = SpanStyle(MaterialTheme.colorScheme.primary)
            )
        )
        append(
            AnnotatedString(
                text = "U",
                spanStyle = SpanStyle(MaterialTheme.colorScheme.secondary)
            )
        )
        append(
            AnnotatedString(
                text = "S",
                spanStyle = SpanStyle(MaterialTheme.colorScheme.primary)
            )
        )
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


