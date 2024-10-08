package common.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun <T> NavigationItem(
    navigationItem: NavigationItemInfo2<T>,
    modifier: Modifier = Modifier,
    selected: Boolean,
    visibilityDelay: Long = 0,
    onFocusing: () -> Unit = {},
    onClick: () -> Unit,
    colors: NavigationItemProps = NavigationItemProps(
        focusedColor = MaterialTheme.colorScheme.secondary,
        unFocusedColor = MaterialTheme.colorScheme.primaryContainer,
        shape = MaterialTheme.shapes.small
        )
) {
    NavigationItem(
        label = navigationItem.label,
        selected = selected,
        visibilityDelay = visibilityDelay,
        onClick = onClick,
        iconText = navigationItem.iconText,
        modifier = modifier,
        onFocusing = onFocusing,
        props = colors
    )

}
@Composable
fun NavigationItem(
    modifier: Modifier = Modifier,
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    onFocusing: () -> Unit = {},
    visibilityDelay: Long = 0L,
    props: NavigationItemProps = NavigationItemProps(
        focusedColor = MaterialTheme.colorScheme.secondary,
        unFocusedColor = MaterialTheme.colorScheme.primaryContainer,
    ),
    iconText: String,
) {
    NavigationItemLayout(
        modifier = modifier,
        label = label,
        visibilityDelay = visibilityDelay,
        selected = selected,
        onClick = onClick,
        onFocusing = onFocusing,
        props = props,
        icon = {
            TextToIcon(
                modifier = Modifier.size(48.dp),
                text = iconText,
                background = props.iconTint,
                labelColor = props.iconLabelColor
            )
        }
    )
}


@Composable
private fun TextToIcon(
    modifier: Modifier = Modifier,
    text: String,
    shape: Shape = CircleShape,
    background: Color,
    labelColor: Color,
) {
    Box(
        modifier = modifier
            .clip(shape).background(background),
        contentAlignment = Alignment.Center
    )
    {
        val annotatedString = AnnotatedString(text)
        Text(
            text = annotatedString,
            style = TextStyle(
                fontWeight = FontWeight.SemiBold,
            ),
            color = labelColor,
            modifier = Modifier.clipToBounds()

        )
    }

}
@Composable
fun <T> NavigationItem(
    modifier: Modifier = Modifier,
    navigationItem: NavigationItemInfo<T>,
    selected: Boolean,
    visibilityDelay: Long = 0,
    onFocusing: () -> Unit = {},
    colors: NavigationItemProps = NavigationItemProps(
        focusedColor = MaterialTheme.colorScheme.secondary,
        unFocusedColor = MaterialTheme.colorScheme.primaryContainer,

        ),
    onClick: () -> Unit,
) {
    NavigationItem(
        modifier = modifier,
        label = navigationItem.label,
        selected = selected,
        onClick = onClick,
        visibilityDelay=visibilityDelay,
        unFocusedIcon = navigationItem.unFocusedIcon,
        focusedIcon = navigationItem.focusedIcon,
        onFocusing = onFocusing,
        props = colors
    )
}


@Composable
fun NavigationItem(
    modifier: Modifier = Modifier,
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    visibilityDelay: Long = 0,
    unFocusedIcon: ImageVector,
    focusedIcon: ImageVector,
    onFocusing: () -> Unit = {},
    props: NavigationItemProps = NavigationItemProps(
        focusedColor = MaterialTheme.colorScheme.secondary,
        unFocusedColor = MaterialTheme.colorScheme.primaryContainer,

        )
) {
    var focused by remember { mutableStateOf(false) }
    val icon = if (focused) focusedIcon else unFocusedIcon
    NavigationItemLayout(
        modifier = modifier,
        visibilityDelay = visibilityDelay,
        label = label,
        selected = selected,
        onClick = onClick,
        onFocusing = {
            onFocusing()
            focused = true
        },
        props = props,
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = null,
            )
        }
    )
}

