package com.just.cse.digital_diary.two_zero_two_three.layers.ui.notes.create_note

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Notes
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.common_ui.WindowSizeDecorator

/**
 * * It used to create  the Notes
 * * It is it is internal to the module can outer module can not directly access it.
 * * It this can handle both compact,medium,expanded window size.
 * @param onExitRequest will be called to exit the from note creation screen
 */
@Composable
fun CreateNote(
    modifier: Modifier=Modifier,
    onTitleChanged: (String) -> Unit,
    onDescriptionChanged: (String) -> Unit,
    data: CreateNoteData,
    onCreate: () -> Unit,
    onExitRequest: () -> Unit,
) {
    WindowSizeDecorator(
        modifier=Modifier,
        onNonExpanded = {
            Scaffold(
                floatingActionButtonPosition = FabPosition.Center,
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = onCreate
                    ) {
                        Row(
                            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.Notes,
                                contentDescription = null
                            )
                            Spacer(Modifier.width(8.dp))
                            Text("Create Note")
                        }

                    }
                }
            )
            {
                CreateNoteForm(
                    modifier = Modifier.padding(it),
                    data = data,
                    onTitleChanged = onTitleChanged,
                    onDescriptionChanged = onDescriptionChanged
                )
            }
        },
        onExpanded = {
            Box(Modifier.fillMaxWidth().padding(16.dp).verticalScroll(rememberScrollState())) {
                Surface(
                    modifier = Modifier.widthIn(max = 500.dp).align(Alignment.TopCenter),
                    shadowElevation = 6.dp
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CreateNoteForm(
                            modifier = Modifier,
                            data = data,
                            onTitleChanged=onTitleChanged,
                            onDescriptionChanged = onDescriptionChanged
                        )
                        Spacer(Modifier.width(12.dp))
                        Row(
                            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                        ) {
                            TextButton(
                                onClick = onExitRequest
                            ) {
                                Text(text = "Cancel")

                            }
                            Spacer(Modifier.width(8.dp))
                            TextButton(
                                onClick = onCreate
                            ) {
                                Text(text = "Create")

                            }

                        }


                    }
                }
            }


        }
    )

}