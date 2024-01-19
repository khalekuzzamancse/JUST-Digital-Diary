package com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.local_destinations.notes_screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.common_ui.WindowSizeDecorator
import com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.local_destinations.note_details.NoteDetails
import com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.local_destinations.shared_notes.NoteListDestination

@Composable
fun NotesScreen() {
    val viewModel = remember { NotesScreenViewModel() }
    val openedNotes = viewModel.openedNote.collectAsState().value
    val notes = @Composable {
        NoteListDestination(
            modifier = Modifier,
            notes = viewModel.notes.collectAsState().value,
            onDetailsOpen = viewModel::onNoteDetailsRequested,
            onExitRequest = {},
        )
    }
    val details: (@Composable () -> Unit) = @Composable {
            AnimatedContent(
                targetState = openedNotes,
                transitionSpec = {
                    slideIntoContainer(
                        animationSpec = tween(durationMillis = 300, easing = EaseIn),
                        towards = AnimatedContentTransitionScope.SlideDirection.End
                    ).togetherWith(
                        slideOutOfContainer(
                            animationSpec = tween(durationMillis = 300, easing = EaseIn),
                            towards = AnimatedContentTransitionScope.SlideDirection.Up
                        )
                    )
                }
            ) { note ->
                if (note != null) {
                    NoteDetails(
                        note = note,
                        onExitRequest = viewModel::onCloseDetailsRequested
                    )
                }
            }

    }

    WindowSizeDecorator(
        showProgressBar = viewModel.showProgressBar.collectAsState().value,
        onCompact = {
            CompactModeLayout(
                noteList = notes,
                noteDetails = details
            )

        },
        onNonCompact = {
            NonCompactModeLayout(
                noteList = {
                    NoteListDestination(
                        modifier = Modifier,
                        notes = viewModel.notes.collectAsState().value,
                        onDetailsOpen = viewModel::onNoteDetailsRequested,
                        onExitRequest = {},
                    )
                },
                noteDetails = details
            )

        }
    )

}