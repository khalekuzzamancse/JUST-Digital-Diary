package com.just.cse.digital_diary.two_zero_two_three.features.others.destination.other

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.common_ui.TopBarDecorator

/**
    In case of NavRail we do not want to show navigation icon that is why
    [enableNavigation] parameter is used
 */

@Composable
internal fun TopBarDecorator(
    enableNavigation: Boolean,
    onExitRequest: () -> Unit = {},
    title: String,
    screen: @Composable () -> Unit,
) {
    TopBarDecorator(
        topBarTitle = title,
        onNavigationIconClick = onExitRequest,
        topNavigationIcon = if (enableNavigation) Icons.Default.Menu else null
    ) {
        Box(Modifier.padding(it)) {
            screen()
        }
    }

}