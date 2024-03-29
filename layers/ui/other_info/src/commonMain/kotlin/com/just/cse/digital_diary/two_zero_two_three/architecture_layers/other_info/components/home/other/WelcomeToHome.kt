package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.components.home.other

import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * * Needed refactor
 * * Since right now unable to read image as shared source,that is why delegating
 * the platform to load the image from their own resource and the logo composable.
 * fix it later using "Res" class  of compose multiplatform resource
 */
@Composable
fun WelcomeToHome(
    modifier: Modifier = Modifier,
    universityLogo: @Composable () -> Unit = {},
    university: @Composable () -> Unit = {},
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Box(
            Modifier.size(width = 200.dp,
                height = 100.dp)
        ) {
            university()
        }
        Spacer(Modifier.height(8.dp))
        AppNameLogoSection(modifier = Modifier, universityLogo = universityLogo)

    }


}

@Composable
private fun AppNameLogoSection(
    modifier: Modifier = Modifier,
    universityLogo: @Composable () -> Unit = {},
) {

    val text = buildAnnotatedString {
        append(
            AnnotatedString(
                text = "J",
                spanStyle = SpanStyle(MaterialTheme.colorScheme.primary)
            )
        )
        append(AnnotatedString(text = "U", spanStyle = SpanStyle(MaterialTheme.colorScheme.error)))
        append(AnnotatedString(text = "S", spanStyle = SpanStyle(MaterialTheme.colorScheme.error)))
        append(
            AnnotatedString(
                text = "T",
                spanStyle = SpanStyle(MaterialTheme.colorScheme.primary)
            )
        )
        append(AnnotatedString(" Digital Diary"))
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            Modifier.size(50.dp),
            contentAlignment = Alignment.Center
        ) {
            universityLogo()
        }
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
            tint = MaterialTheme.colorScheme.primary
        )

    }

}