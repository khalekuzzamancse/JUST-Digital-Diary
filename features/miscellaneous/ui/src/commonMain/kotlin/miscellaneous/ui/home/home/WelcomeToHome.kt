package miscellaneous.ui.home.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material3.Icon
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
import justdigitaldiary.features.miscellaneous.ui.generated.resources.just_gate
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
@OptIn(ExperimentalResourceApi::class)
@Composable
fun WelcomeToHome(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        val res: DrawableResource = Res.drawable.just_gate
        //after that compile it again to generate Res class, use : .\gradlew generateComposeResClass
        Image(
            modifier=Modifier.size(100.dp),
            painter = painterResource(res),//org.jetbrains.compose.resources.
            contentDescription = null,
        )
        Spacer(Modifier.height(8.dp))
        AppNameLogoSection(modifier = Modifier, varsityLogoSize = 100.dp)

    }


}

@OptIn(ExperimentalResourceApi::class)
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
        val res: DrawableResource = Res.drawable.just_logo_trans
        //after that compile it again to generate Res class, use : .\gradlew generateComposeResClass
        Image(
            modifier=Modifier.size(varsityLogoSize),
            painter = painterResource(res),//org.jetbrains.compose.resources.
            contentDescription = null,
        )
        Spacer(Modifier.width(4.dp))
        Text(
            text = text,
            style = TextStyle(
                fontWeight = FontWeight.ExtraBold,
                fontSize = 16.sp,
                fontFamily = FontFamily.Cursive
            )
        )
        Spacer(Modifier.width(8.dp))
        Icon(
            modifier = Modifier.size(50.dp),
            imageVector = Icons.AutoMirrored.Filled.MenuBook,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.tertiary
        )

    }

}