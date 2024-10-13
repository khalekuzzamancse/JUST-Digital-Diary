package common.ui

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
import androidx.compose.material.icons.automirrored.outlined.Message
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.ExpandLess
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest


@Composable
fun GenericEmployeeCard(
    modifier: Modifier,
    expandMode: Boolean = false,
    showEditButton: Boolean,
    showDeleteButton: Boolean,
    name: String,
    profileImageUrl: String?,
    onCallRequest: () -> Unit,
    onEmailRequest: () -> Unit,
    onMessageRequest: () -> Unit,
    onEditRequest: () -> Unit,
    onDeleteRequest: () -> Unit,
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            _ProfileImage(imageURl = profileImageUrl)
            _NonExpandableInfo(name = name, details = details)
//            if (expandMode) {
//                ExpandAbleInfo(
//                    modifier = Modifier, name = name, details = details
//                )
//            } else {
//                _NonExpandableInfo(name = name, details = details)
//            }
            Spacer(Modifier.height(8.dp))
            _Controls(
                showEditButton = showDeleteButton,
                showDeleteButton = showDeleteButton,
                onCallRequest = onCallRequest,
                onMessageRequest = onMessageRequest,
                onEmailRequest = onEmailRequest,
                onDeleteRequest = onDeleteRequest,
                onEditRequest = onEditRequest,
            )

        }

    }

}

@Composable
private fun ColumnScope._NonExpandableInfo(
    modifier: Modifier = Modifier,
    name: String,
    details: @Composable () -> Unit,
) {
    EmployeeName(
        name = name,
        modifier = Modifier.align(Alignment.CenterHorizontally)
    )
    details()
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ExpandAbleInfo(
    modifier: Modifier = Modifier,
    name: String,
    details: @Composable () -> Unit
) {
    var expanded by remember { mutableStateOf(true) }

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
    tint: Color = MaterialTheme.colorScheme.primary,
    onCallRequest: () -> Unit,
    onEmailRequest: () -> Unit,
    onMessageRequest: () -> Unit,
    showEditButton: Boolean,
    showDeleteButton: Boolean,
    onEditRequest: () -> Unit,
    onDeleteRequest: () -> Unit,
) {
    Column {
        FlowRow(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {

            _ControlIconButton(
                onClick = onCallRequest,
                icon = Icons.Outlined.Call,
                tint = tint
            )

            _ControlIconButton(
                onClick = onEmailRequest,
                icon = Icons.Outlined.Email,
                tint = tint
            )
            _ControlIconButton(
                onClick = onMessageRequest,
                icon = Icons.AutoMirrored.Outlined.Message,
                tint = tint
            )
            if (showEditButton) {
                _ControlIconWithConfirmationDialog(
                    onConfirmRequest = onEditRequest,
                    icon = Icons.Outlined.Edit,
                    tint = MaterialTheme.colorScheme.secondary,
                    message = "Are you sure you want to edit?"
                )
            }
            if (showDeleteButton) {
                _ControlIconWithConfirmationDialog(
                    onConfirmRequest = onDeleteRequest,
                    icon = Icons.Outlined.Delete,
                    tint =  MaterialTheme.colorScheme.secondary,
                    message = "Are you sure you want to delete?"
                )
            }


        }

    }

}

@Composable
private fun _ControlIconButton(
    onClick: () -> Unit,
    icon: ImageVector,
    tint: Color
) {
    IconButton(onClick = onClick) {
        Icon(imageVector = icon, contentDescription = null, tint = tint)
    }
}

@Composable
private fun ColumnScope._ProfileImage(
    modifier: Modifier = Modifier,
    imageURl: String?
) {
    if (!imageURl.isNullOrBlank()) {
        var loadFailed by remember { mutableStateOf(false) } // Track load failure

        if (loadFailed) {
            // If the load failed, show the placeholder
            ProfileImagePlaceHolder(
                modifier = Modifier
                    .height(100.dp)
                    .width(150.dp)
                    .align(Alignment.CenterHorizontally),
            )
        } else {
            // Attempt to load the image using AsyncImage
            AsyncImage(
                model = ImageRequest.Builder(LocalPlatformContext.current)
                    .data(imageURl)
                    .build(),
                contentDescription = null,
                onState = { state ->
                    when (state) {
                        is AsyncImagePainter.State.Error -> {
                            // If loading fails, mark loadFailed as true to show the placeholder
                            loadFailed = true
                        }

                        else -> {
                            // Do nothing, allow the image to load
                        }
                    }
                },
                modifier = modifier
                    .heightIn(max = 100.dp)
                    .widthIn(max = 150.dp)
                    .align(Alignment.CenterHorizontally),
            )
        }
    } else {
        // Show the placeholder if the URL is null or blank
        ProfileImagePlaceHolder(
            modifier = Modifier
                .height(100.dp)
                .width(150.dp)
                .align(Alignment.CenterHorizontally),
        )
    }
}

@Composable
private fun _ControlIconWithConfirmationDialog(
    onConfirmRequest: () -> Unit,
    icon: ImageVector,
    tint: Color,
    message: String
) {
    /**
     * - This just for confirmation, so it make sense  to not hoist the state
     */
    var showDialog by rememberSaveable { mutableStateOf(false) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    onConfirmRequest() // Perform the confirmed action
                }) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            },
            text = { Text(message) }
        )
    }

    IconButton(onClick = { showDialog = true }) {
        Icon(imageVector = icon, contentDescription = null, tint = tint)
    }
}