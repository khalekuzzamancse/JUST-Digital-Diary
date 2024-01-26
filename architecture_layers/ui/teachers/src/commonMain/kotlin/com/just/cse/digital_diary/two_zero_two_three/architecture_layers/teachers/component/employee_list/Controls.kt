package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.teachers.component.employee_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Dialpad
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Message
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun Controls(
    modifier: Modifier = Modifier,
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
                Icons.Default.Call,
                null
            )
        }
        IconButton(
            onClick = onEmailRequest,
        ) {
            Icon(
                Icons.Default.Email,
                null
            )

        }
        IconButton(
            onClick = onMessageRequest
        ) {
            Icon(
                Icons.Default.Message,
                null
            )

        }
    }

}