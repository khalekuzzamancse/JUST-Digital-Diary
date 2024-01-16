package com.just.cse.digital_diary.features.common_ui.navigation

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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
 fun <T> AnimatedNavigationItem(
    navigationItem: NavigationItem<T>,
    selected: Boolean,
    visibilityDelay: Long=0,
    onFocusing: () -> Unit={},
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
            selected = selected,
            focusedIcon = navigationItem.focusedIcon,
            onClick = onClick,
            unFocusedIcon = navigationItem.unFocusedIcon,
            label = navigationItem.label,
            onFocusing = onFocusing
        )
    }
}

@Composable
 private fun NavigationItem(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    unFocusedIcon: ImageVector,
    focusedIcon: ImageVector,
    onFocusing: () -> Unit={},
    shape: Shape = RoundedCornerShape(topStart = 8.dp, bottomEnd = 8.dp),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
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
    if(focusing){
        onFocusing()
    }
    val backgroundColor by animateColorAsState(
        targetValue = if (focusing) MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.primaryContainer
    )
    val icon = if (focusing) focusedIcon else unFocusedIcon
    val infiniteTransition = rememberInfiniteTransition()
    val selectionColor by infiniteTransition.animateColor(
        initialValue =MaterialTheme.colorScheme.primary,
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
                if(focusing){
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

