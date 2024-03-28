package navigation.modal_drawer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material3.HorizontalDivider
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

@Composable
fun DrawerHeader(
    modifier: Modifier = Modifier,
    onLogOutRequest: () -> Unit,
) {
    Column(
        modifier = modifier
            .padding(
                start = 4.dp,
                top = 32.dp,
                bottom =32.dp,
                end = 8.dp
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            LogOutButton(onLogOutRequest)
            Spacer(Modifier.width(8.dp))
            AppName()
            Spacer(Modifier.width(4.dp))
            AppLogo()

        }
        Spacer(Modifier.height(16.dp))
        HorizontalDivider(
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.height(4.dp))
    }

}

@Composable
private fun AppLogo() {
    Icon(
        imageVector = Icons.AutoMirrored.Filled.MenuBook,
        contentDescription = null,
        tint = MaterialTheme.colorScheme.primary
    )
}

@Composable
private fun AppName() {
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
    Text(
        text = text, style = TextStyle(
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp,
            fontFamily = FontFamily.Cursive
        )
    )

}

@Composable
private fun LogOutButton(
    onClick: () -> Unit,
) {
    Icon(
        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
        contentDescription = "logout button",
        tint = MaterialTheme.colorScheme.secondary,
        modifier = Modifier.clickable { onClick() }
    )

}
