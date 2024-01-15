package com.just.cse.digital_diary.features.departments.navgraph.local_destinations.home.bottom_sheet

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CloseFullscreen
import androidx.compose.material.icons.filled.OpenInFull
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun BottomSheetTopBar(
    sheetValue: SheetValue,
    title: String,
    onNavigationIconClick: () -> Unit,
    onToggleBottomSheet: () -> Unit,
    onSearchRequest: () -> Unit,
) {
    val sheetIcon = if (sheetValue==SheetValue.Expanded) Icons.Default.OpenInFull else Icons.Default.CloseFullscreen

    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(
                onClick = onNavigationIconClick
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
        },
        actions = {
            IconButton(
                onClick = onToggleBottomSheet
            ) {
                Icon(
                    imageVector = sheetIcon,
                    contentDescription = null
                )
            }
            IconButton(
                onClick = onSearchRequest
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            }

        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(8.dp)
        )
    )


}