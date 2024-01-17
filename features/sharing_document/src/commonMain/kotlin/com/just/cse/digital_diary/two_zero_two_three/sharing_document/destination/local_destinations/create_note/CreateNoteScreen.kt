package com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.local_destinations.create_note

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
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notes
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.common_ui.WindowSizeDecorator
import com.just.cse.digital_diary.two_zero_two_three.common_ui.progressbar.ProgressBarNSnackBarDecorator
import com.just.cse.digital_diary.two_zero_two_three.common_ui.top_bar.SimpleTopBar

@Composable
internal fun CreateNoteScreen(
    onExitRequest:()->Unit,
) {
    val viewModel= remember { CreateNoteViewModel() }
    WindowSizeDecorator(
        showProgressBar = viewModel.showProgressBar.collectAsState().value,
        snackBarMessage = viewModel.snackBarMessage.collectAsState().value,
        onCompact = {
            Scaffold (
                topBar = {
                    SimpleTopBar(
                        title = "Note Creation",
                        onNavigationIconClick =onExitRequest
                    )
                },
                floatingActionButtonPosition = FabPosition.Center,
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = viewModel::onCreateRequest
                    ) {
                        Row(
                            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Notes,
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
                    data=viewModel.data.collectAsState().value,
                    onTitleChanged=viewModel::onTitleChanged,
                    onDescriptionChanged=viewModel::onDescriptionChanged
                )
            }
        },
        onNonCompact = {
            Box(Modifier.fillMaxWidth().padding(16.dp).verticalScroll(rememberScrollState())){
                Surface (modifier = Modifier.widthIn(max=500.dp).align(Alignment.TopCenter), shadowElevation = 6.dp){
                    Column(
                        modifier =  Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CreateNoteForm(
                            modifier = Modifier,
                            data=viewModel.data.collectAsState().value,
                            onTitleChanged=viewModel::onTitleChanged,
                            onDescriptionChanged=viewModel::onDescriptionChanged
                        )
                        Spacer(Modifier.width(12.dp))
                        Row(
                            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                        ) {
                            TextButton(
                                onClick = onExitRequest
                            ){
                                Text(text="Cancel")

                            }
                            Spacer(Modifier.width(8.dp))
                            TextButton(
                                onClick =  viewModel::onCreateRequest
                            ){
                                Text(text="Create")

                            }

                        }


                    }
                }
            }


        }
    )

}