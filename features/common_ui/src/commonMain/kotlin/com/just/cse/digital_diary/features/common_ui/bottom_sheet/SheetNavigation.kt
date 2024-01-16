package com.just.cse.digital_diary.features.common_ui.bottom_sheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.features.common_ui.navigation.AnimatedNavigationItem
import com.just.cse.digital_diary.features.common_ui.navigation.NavigationItem

@OptIn(ExperimentalLayoutApi::class)
@Composable
 fun <T> BottomSheetNavigationSection(
    destinations: List<NavigationItem<T>>,
    currentDestinationIndex: Int,
    onItemClick: (Int) -> Unit,
) {
    Surface(
        modifier = Modifier.padding(8.dp),
        shadowElevation = 8.dp,
        shape = RoundedCornerShape(topStart = 4.dp)
    ) {
        FlowRow(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            destinations.forEachIndexed { index, _ ->
                AnimatedNavigationItem(
                    navigationItem = destinations[index],
                    visibilityDelay = (index + 1) * 10L,
                    selected = currentDestinationIndex == index,
                    onClick = {
                        onItemClick(index)
                    }
                )
            }

        }
    }


}