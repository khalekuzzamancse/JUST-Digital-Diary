package com.just.cse.digital_diary.two_zero_two_three.root_home.local_destionations.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Segment
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppbar(
    navigationIcon: ImageVector = Icons.Filled.Menu,
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
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(8.dp)
        )
    )
}
