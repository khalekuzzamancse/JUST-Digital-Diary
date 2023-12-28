package com.just.cse.digital_diary.two_zero_two_three.root_home.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Segment
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp


@Composable
fun RootHomeContent(
    onCreateNote: () -> Unit = {},
    openDrawer: () -> Unit = {},
    onLogOut: () -> Unit = {},
){
    var username by remember {
        mutableStateOf("NULL")
    }

    val onNavigationIconClick: () -> Unit = {
        openDrawer()
    }

    Scaffold(
        topBar = {
            HomeTopAppbar(
                onNavigationIconClick = onNavigationIconClick,
                onLogOut = onLogOut
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onCreateNote()
            }) {
                Icon(Icons.Filled.Add, null)
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {

        Column(
            modifier = Modifier
                .padding(it)
                .padding(start = 16.dp)
                .fillMaxSize()
        ) {
            UserInfo(username = username)
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppbar(
    navigationIcon: ImageVector = Icons.Filled.Segment,
    onNavigationIconClick: () -> Unit = {},
    onLogOut: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = "Home") },
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(navigationIcon, null)
            }
        },
        actions = {
            HomeScreenDropDown(
                onLogOutIconClick = onLogOut
            )


        },
    )
}
