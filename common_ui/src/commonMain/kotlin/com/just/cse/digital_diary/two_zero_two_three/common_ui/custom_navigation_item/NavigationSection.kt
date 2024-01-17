package com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun <T> AnimatedNavigationItemTextIcon(
    navigationItem: NavigationItemInfo2<T>,
    modifier: Modifier = Modifier,
    selected: Boolean,
    visibilityDelay: Long = 0,
    onFocusing: () -> Unit = {},
    onClick: () -> Unit,
    colors:NavigationItemColor = NavigationItemColor(
        focusedColor =MaterialTheme.colorScheme.errorContainer ,
        unFocusedColor =MaterialTheme.colorScheme.primaryContainer,

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
        NavigationItemTextIcon(
            modifier = modifier,
            colors=colors,
            label = navigationItem.label,
            selected = selected,
            onClick = onClick,
            iconText = navigationItem.iconText,
            onFocusing = onFocusing
        )
    }
}

@Composable
fun <T> AnimatedNavigationItemTextIcon(
    modifier: Modifier = Modifier,
    navigationItem: NavigationItemInfo<T>,
    selected: Boolean,
    visibilityDelay: Long = 0,
    onFocusing: () -> Unit = {},
    colors:NavigationItemColor = NavigationItemColor(
        focusedColor =MaterialTheme.colorScheme.errorContainer ,
        unFocusedColor =MaterialTheme.colorScheme.primaryContainer,

        ),
    onClick: () -> Unit,
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
        NavigationItem(
            modifier = modifier,
            selected = selected,
            colors = colors,
            focusedIcon = navigationItem.focusedIcon,
            onClick = onClick,
            unFocusedIcon = navigationItem.unFocusedIcon,
            label = navigationItem.label,
            onFocusing = onFocusing
        )
    }
}

data class NavigationItemColor(
    val focusedColor: Color,
    val unFocusedColor: Color,
)

@Composable
fun NavigationItem(
    modifier: Modifier = Modifier,
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    unFocusedIcon: ImageVector,
    focusedIcon: ImageVector,
    onFocusing: () -> Unit = {},
    shape: Shape = RoundedCornerShape(topStart = 8.dp, bottomEnd = 8.dp),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors:NavigationItemColor = NavigationItemColor(
        focusedColor =MaterialTheme.colorScheme.errorContainer ,
        unFocusedColor =MaterialTheme.colorScheme.primaryContainer,

    )
    ) {

    var focusing by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    scope.launch {
        interactionSource.interactions.collect {
            //hover for non touch desktop and web ,press for touch devices
            focusing = it is HoverInteraction.Enter
            if (it is PressInteraction) {
                focusing = true
                delay(1000)
                focusing = false
            }


        }

    }
    if (focusing) {
        onFocusing()
    }
    val backgroundColor by animateColorAsState(
        targetValue = if (focusing) colors.focusedColor else colors.unFocusedColor
    )
    val icon = if (focusing) focusedIcon else unFocusedIcon
    val infiniteTransition = rememberInfiniteTransition()
    val selectionColor by infiniteTransition.animateColor(
        initialValue = MaterialTheme.colorScheme.primary,
        targetValue = MaterialTheme.colorScheme.inverseOnSurface,
        animationSpec = infiniteRepeatable(tween(durationMillis = 1000, easing = LinearEasing))
    )

    Surface(
        shadowElevation = 2.dp,
        selected = selected,
        onClick = onClick,
        modifier = modifier.semantics { role = Role.Tab },
        shape = shape,
        color = backgroundColor,
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
            Icon(
                imageVector = icon,
                contentDescription = null,
            )
            Spacer(Modifier.width(12.dp))
            Text(text = label)
        }
    }
}

