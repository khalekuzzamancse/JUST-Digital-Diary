package com.just.cse.digital_diary.two_zero_two_three.department.local_destinations.home

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DepartmentTopAppbar(
    modifier: Modifier = Modifier,
    title: String,
    navigationIcon: ImageVector? = null,
    onNavigationIconClick: () -> Unit = {},
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(text = title)
        },
        navigationIcon = {
            if (navigationIcon != null) {
                IconButton(
                    onClick = onNavigationIconClick
                ) {
                    Icon(
                        navigationIcon, null
                    )
                }
            }

        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(8.dp)
        )

    )

}