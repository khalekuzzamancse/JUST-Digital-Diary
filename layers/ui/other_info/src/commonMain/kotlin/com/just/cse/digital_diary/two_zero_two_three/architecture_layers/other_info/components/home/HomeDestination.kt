package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.components.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.components.event_gallery.state.Event
import com.just.cse.digital_diary.two_zero_two_three.common_ui.top_bar.SimpleTopBar


@Composable
fun HomeDestination(
    onEvent:(HomeDestinationEvent)->Unit,
){
    var username by remember {
        mutableStateOf<String?>(null)
    }
    LaunchedEffect(Unit){
//       val userInfo= SignedInUserRepository.getUserInfo()
       // username=userInfo.name
    }

    val onNavigationIconClick: () -> Unit = {
       onEvent(HomeDestinationEvent.NavigationRequest)
    }


    Scaffold(
        topBar = {
            SimpleTopBar(
                onNavigationIconClick = onNavigationIconClick,
               title = "Home",
                navigationIcon = Icons.Default.Menu
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(HomeDestinationEvent.NoteCreateRequest)
            }) {
                Icon(Icons.Filled.Add, null)
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(start = 16.dp)
                .fillMaxSize()
        ) {
            username?.let { UserInfo(username = it) }
            Spacer(modifier = Modifier.height(16.dp))



        }


    }


}

@Composable
fun UserInfo(
    modifier: Modifier = Modifier,
    username: String = "",
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Hello , $username !",
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            text = "Have a nice day!",
            style = MaterialTheme.typography.labelLarge,
        )
    }
}



