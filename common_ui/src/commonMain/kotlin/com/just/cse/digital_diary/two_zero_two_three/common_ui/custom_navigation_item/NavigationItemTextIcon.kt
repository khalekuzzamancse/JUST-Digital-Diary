package com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item

import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
private fun TextToIcon(
    modifier: Modifier = Modifier,
    text: String,
    shape: Shape = CircleShape,
    tint: Color = Color.Red,
    color: Color = Color.White
) {
    Box( modifier=modifier
        .clip(shape).
        background(tint),
        contentAlignment = Alignment.Center
    )
    {
        val annotatedString= AnnotatedString(text)
        Text(
            text = annotatedString,
            style = TextStyle(
                fontWeight = FontWeight.SemiBold,
            ),
            color=color,
            modifier= Modifier.clipToBounds()

        )
    }

}
@Composable
 fun NavigationItemTextIcon(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    iconText: String,
    modifier: Modifier = Modifier,
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
//    val icon = if (focusing) focusedIcon else unFocusedIcon
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
                if(focusing){
                    drawRect(
                        color = selectionColor,
                        style = Stroke(width = 2f)
                    )
                }
            }.padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            TextToIcon(
                modifier = Modifier.size(48.dp),
                text = iconText
            )
            Spacer(Modifier.width(12.dp))
            Text(text = label)
        }
    }
}

