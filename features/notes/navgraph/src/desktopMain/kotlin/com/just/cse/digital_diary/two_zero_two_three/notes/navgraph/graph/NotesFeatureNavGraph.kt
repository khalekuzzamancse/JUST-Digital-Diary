package com.just.cse.digital_diary.two_zero_two_three.notes.navgraph.graph

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Notes
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.just.cse.digital_diary.two_zero_two_three.common_ui.top_bar.SimpleTopBar
import com.just.cse.digital_diary.two_zero_two_three.notes.navgraph.screens.CreateNoteScreen
import com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.functionalities.note_list.ListOfNotes

@Composable
fun NotesFeatureNavGraph() {
    Navigator(EmptyScreen) {navigator->
        Scaffold(
            modifier = Modifier,
            topBar = {
                SimpleTopBar(
                    title = "Notes",
                    navigationIcon = Icons.Default.ArrowBack,
                    onNavigationIconClick = {}
                )
            },
            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    navigator.push(
                        CreateNoteScreen(
                            onExitRequest ={
                                navigator.pop()
                            }
                        )
                    )

                }) {
                    Row(
                        modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Notes,
                            contentDescription = null
                        )
//                        Spacer(Modifier.width(8.dp))
//                        Text("Note Creation")
                    }
                }
            }
        ) {
            SlideTransition(navigator)
        }
    }
}

private object EmptyScreen : Screen {
    @Composable
    override fun Content() {
       // ListOfNotes(modifier = Modifier)
    }
}