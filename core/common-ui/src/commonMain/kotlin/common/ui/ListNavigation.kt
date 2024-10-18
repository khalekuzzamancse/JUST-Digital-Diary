package common.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


data class NavigationItemInfo(
    val key:String,
    val label: String,
    val unFocusedIcon: ImageVector,
    val focusedIcon: ImageVector =unFocusedIcon,
    val route: String = label,
    val badge: String? = null,
)
data class NavigationItemInfo2<T>(
    val key:String,
    val label: String,
    val iconText: String,
    val route: String = label,
    val badge: String? = null,
)
@Composable
internal fun NavigationItemLayout(
    modifier: Modifier = Modifier,
    visibilityDelay: Long = 0,
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    onFocusing: () -> Unit = {},
    props: NavigationItemProps = NavigationItemProps(
        focusedColor = MaterialTheme.colorScheme.errorContainer,
        unFocusedColor = MaterialTheme.colorScheme.primaryContainer
    )
) {
    var show by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(visibilityDelay)
            show = true
            break
        }
    }
    AnimatedVisibility(
        visible = show
    ) {
        NavigationItemLayoutCore(
            modifier = modifier,
            label = label,
            selected = selected,
            onClick = onClick,
            onFocusing = onFocusing,
            props = props,
            icon = icon
        )
    }
}


@Composable
private fun NavigationItemLayoutCore(
    modifier: Modifier = Modifier,
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    onFocusing: () -> Unit = {},
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    props: NavigationItemProps = NavigationItemProps(
        focusedColor = MaterialTheme.colorScheme.errorContainer,
        unFocusedColor = MaterialTheme.colorScheme.primaryContainer,
    )
) {

    var focusing by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    scope.launch {
        interactionSource.interactions.collect {
            //hover for non touch desktop and web ,press for touch devices
            focusing = it is HoverInteraction.Enter

        }

    }
    if (focusing) {
        onFocusing()
    }
//    val backgroundColor by animateColorAsState(
//        targetValue = if (focusing) props.focusedColor else props.unFocusedColor
//    )

    val selectionColor = props.focusedColor
    val itemColor = if (selected) props.focusedColor else props.unFocusedColor
    Surface(
        shadowElevation = 2.dp,
        selected = selected,
        onClick = onClick,
        modifier = modifier.semantics { role = Role.Tab },
        shape = props.shape,
        color = itemColor,
        interactionSource = interactionSource,
    ) {
        Row(
            Modifier.drawBehind {
                if (focusing) {
                    drawRect(
                        color = selectionColor,
                        style = Stroke(width = 2f)
                    )
                }
            }.padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon()
            Spacer(Modifier.width(12.dp))
            Text(text = label, color = MaterialTheme.colorScheme.contentColorFor(itemColor))
        }
    }
}

data class NavigationItemProps(
    val focusedColor: Color,
    val unFocusedColor: Color,
    val iconLabelColor: Color = Color.Unspecified,
    val iconTint: Color = Color.Unspecified,
    val shape: Shape = RoundedCornerShape(8.dp),
)

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
fun NavigationItem(
    modifier: Modifier = Modifier,
    navigationItem: NavigationItemInfo,
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

