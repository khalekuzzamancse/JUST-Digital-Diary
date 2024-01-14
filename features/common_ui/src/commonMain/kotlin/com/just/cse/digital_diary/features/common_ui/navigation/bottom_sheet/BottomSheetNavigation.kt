package com.just.cse.digital_diary.features.common_ui.navigation.bottom_sheet

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.features.common_ui.navigation.NavigationItem
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> BottomSheetNavigation(
    visibilityDelay: Long,
    destinations: List<NavigationItem<T>>,
    selectedDesertionIndex: Int,
    onDestinationSelected: (Int) -> Unit = {},
    sheetState: SheetState,
    topBar: @Composable (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    BottomSheetDecorator(
        topBar = topBar,
        sheetState = sheetState,
        sheetContent = {
                BottomSheetNavigation(
                    visibilityDelay=visibilityDelay,
                    destinations = destinations,
                    destinationDecorator = {index->
                        BottomSheetItemDefaultDecorator(
                            selected = selectedDesertionIndex == index,
                            onClick = {
                                onDestinationSelected(index)
                            },
                            navigationItem = destinations[index]
                        )
                    }
                )

        },
        content = {
            content()
        }
    )
}
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun <T> BottomSheetNavigation(
    visibilityDelay: Long,
    destinations: List<NavigationItem<T>>,
    destinationDecorator: @Composable (index: Int) -> Unit,
) {
    var visible by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(Unit) {
        while (true) {
            delay(visibilityDelay)
            visible = true
            break
        }
    }
    FlowRow(modifier = Modifier
        .fillMaxWidth().
        verticalScroll(rememberScrollState())) {
        destinations.forEachIndexed { index, _ ->
            AnimatedVisibility(
               visible =  visible
            ){
                destinationDecorator(index)
            }

        }
    }


}


