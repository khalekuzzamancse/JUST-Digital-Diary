package com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.NavigationItem
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.NavigationRails
import kotlinx.coroutines.launch

//Scaffold need for snack bar
//No top bar and bottom bar,since it is for medium and expanded screen
//No navigation drawer allowed

//Completely Stateless Composable,



//Content itself should be any composable such as
//Scaffold if a snack-bar is needed.


@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun NavigationRailLayout(
    selectedDestinationIndex: Int,
    destinations: List<NavigationItem>,
    onNavRailItemSelected: (Int) -> Unit,
    snackBarMessage: String? = null,
    content: @Composable (Modifier) -> Unit
) {
    val screenType = calculateWindowSizeClass().widthSizeClass
    Row(modifier = Modifier) {
        NavigationRails(
            modifier = Modifier,
            destinations = destinations,
            selectedDestinationIndex = selectedDestinationIndex,
            isExpanded = screenType == WindowWidthSizeClass.Expanded,
            onSelectionChanged = onNavRailItemSelected
        )
        content(Modifier.weight(1f))
    }

}



