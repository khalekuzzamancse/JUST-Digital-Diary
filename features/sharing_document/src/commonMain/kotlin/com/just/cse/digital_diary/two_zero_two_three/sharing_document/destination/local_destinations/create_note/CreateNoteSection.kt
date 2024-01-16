package com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.local_destinations.create_note

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notes
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.common_ui.progressbar.ProgressBarNSnackBarDecorator
import com.just.cse.digital_diary.two_zero_two_three.common_ui.top_bar.SimpleTopBar

@Composable
internal fun CreateNoteSection(
    onExitRequest:()->Unit,
) {
    val viewModel= remember { CreateNoteViewModel() }
    ProgressBarNSnackBarDecorator(
        modifier = Modifier,
        showProgressBar = viewModel.showProgressBar.collectAsState().value,
        snackBarMessage = viewModel.snackBarMessage.collectAsState().value
    ){
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

        ){
            Column (
                modifier = Modifier.padding(it).padding(16.dp)
            ){
              CreateNoteForm(
                    data=viewModel.data.collectAsState().value,
                    onTitleChanged=viewModel::onTitleChanged,
                    onDescriptionChanged=viewModel::onDescriptionChanged
                )
            }


        }
    }

}