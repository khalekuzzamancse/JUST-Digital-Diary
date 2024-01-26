package com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.faculties.component.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CloseFullscreen
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.OpenInFull
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeTopBar(
    title: String,
    onNavigationIconClick: () -> Unit,
    sheetController:@Composable () -> Unit,
) {
        TopAppBar(
            title = {
                Text(text = title)
            },
            navigationIcon = {
                IconButton(
                    onClick = onNavigationIconClick
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = null
                    )
                }
            },
            actions = {
                sheetController()


            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(8.dp)
            )
        )


}