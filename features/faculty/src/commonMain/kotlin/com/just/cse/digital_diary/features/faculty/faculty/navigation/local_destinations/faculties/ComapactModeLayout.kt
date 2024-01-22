package com.just.cse.digital_diary.features.faculty.faculty.navigation.local_destinations.faculties

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.features.faculty.faculty.navigation.local_destinations.home.HomeTopBar
import com.just.cse.digital_diary.two_zero_two_three.common_ui.bottom_sheet.decorator.BottomSheetDecorator
import com.just.cse.digital_diary.two_zero_two_three.common_ui.bottom_sheet.handler.BottomSheetControllerIcon
import com.just.cse.digital_diary.two_zero_two_three.common_ui.bottom_sheet.handler.BottomSheetHandler
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CompactScreenLayout(
    facultyDestinations: @Composable () -> Unit,
    departmentDestinations: (@Composable () -> Unit)? = null,
    sheetHandler: BottomSheetHandler,
    onExitRequest: () -> Unit,
    content: (@Composable () -> Unit)? = null,
) {
    /*
  Note recomposition the faculty destination because recomposition causes animation
                as,we have animation when showing department destination as a result ,to give better user                experience not hiding(recompose) the faculties,
                 Use a department background that hide the faculties  without recomposing the faculties
                  */

    Box(modifier = Modifier.fillMaxSize()) {
        BottomSheetDecorator(
            topBar = {
                HomeTopBar(
                    title = "Faculty Info",
                    onNavigationIconClick = onExitRequest,
                    sheetController = {
                        BottomSheetControllerIcon(
                            handler = sheetHandler
                        )
                    },

                )
            },
            sheetState = sheetHandler.sheetState.collectAsState().value,
            sheetContent = {
                facultyDestinations()
            },
            content = {
                if (content != null)
                    content()
            }
        )

        if (departmentDestinations != null) {
            Box(
                Modifier.matchParentSize().background(MaterialTheme.colorScheme.secondaryContainer)
            ) {
                departmentDestinations()
            }

        }



    }


}
