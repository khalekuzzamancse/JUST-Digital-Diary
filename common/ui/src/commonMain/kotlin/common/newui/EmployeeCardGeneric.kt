package common.newui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.outlined.ExpandLess
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import common.ui.network_image.ImageLoader


@Composable
fun GenericEmployeeCard(
    modifier: Modifier,
    expandMode: Boolean ,
    name: String,
    profileImageUrl: String? = null,
    onCallRequest: () -> Unit,
    onEmailRequest: () -> Unit,
    onMessageRequest: () -> Unit,
    details: @Composable () -> Unit,
) {
    Surface(
        modifier = modifier,
        shadowElevation = 2.dp
    ) {
        Column(
            modifier = modifier
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.Start
        ) {
            _ProfileImage(imageURl = profileImageUrl)
            if (expandMode) {
                ExpandAbleInfo(
                    modifier = Modifier, name = name, details = details
                )
            } else {
                EmployeeName(
                    name = name,
                    modifier = Modifier
                )
                details()
            }
            Spacer(Modifier.height(8.dp))
            _Controls(
                onCallRequest = onCallRequest,
                onMessageRequest = onMessageRequest,
                onEmailRequest = onEmailRequest
            )

        }

    }

}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ExpandAbleInfo(
    modifier: Modifier = Modifier,
    name: String,
    details: @Composable () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.Start
    ) {
        FlowRow(
            modifier = Modifier,
//            verticalAlignment = Alignment.CenterVertically
        ) {
            //what if the name doesn't fit into one Row?,then button will be hidden
            EmployeeName(
                name = name,
                modifier = Modifier
            )
            Icon(
                imageVector = if (expanded) Icons.Outlined.ExpandLess else Icons.Outlined.ExpandMore,
                contentDescription = null,
                modifier = Modifier.clickable {
                    expanded = !expanded
                }
            )
        }
        AnimatedVisibility(
            visible = expanded,
            enter = fadeIn() + expandIn(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ),
            exit = shrinkOut() + fadeOut()
        ) {
            details()
        }

    }

}

@Composable
private fun EmployeeName(
    modifier: Modifier,
    name: String,
) {
    Text(
        modifier = modifier,
        text = name,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        fontFamily = FontFamily.Monospace
    )

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun _Controls(
    modifier: Modifier = Modifier,
    tint: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.primary,
    onCallRequest: () -> Unit,
    onEmailRequest: () -> Unit,
    onMessageRequest: () -> Unit,
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        IconButton(
            onClick = onCallRequest
        ) {
            Icon(
                imageVector = Icons.Default.Call,
                contentDescription = null,
                tint = tint
            )
        }
        IconButton(
            onClick = onEmailRequest,
        ) {
            Icon(
                Icons.Default.Email,
                null,
                tint = tint
            )

        }
        IconButton(
            onClick = onMessageRequest
        ) {
            Icon(
                Icons.AutoMirrored.Filled.Message,
                null,
                tint = tint
            )

        }
    }

}

@Composable
private fun ColumnScope._ProfileImage(
    modifier: Modifier = Modifier,
    imageURl: String?
) {
    if (imageURl != null) {
        ImageLoader(
            url = imageURl,
            modifier = modifier
                .heightIn(max = 100.dp)
                .widthIn(max = 150.dp)
                .align(Alignment.CenterHorizontally),
        )
    } else {
        ProfileImagePlaceHolder(
            modifier = Modifier
                .height(100.dp)
                .width(150.dp).align(Alignment.CenterHorizontally),
        )
    }

}
