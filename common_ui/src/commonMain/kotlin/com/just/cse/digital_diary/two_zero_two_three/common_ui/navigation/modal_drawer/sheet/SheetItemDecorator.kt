package com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.modal_drawer.sheet

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item.NavigationItemInfo
import kotlinx.coroutines.delay

@Composable
fun <T> ItemDecorator(
    navigationItem: NavigationItemInfo<T>,
    isSelected: Boolean,
    visibilityDelay: Long,
    onClick: () -> Unit,
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
    AnimatedVisibility(
        visible=visible
    ){
        NavigationDrawerItem(
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
            icon = {
                Icon(
                    navigationItem.unFocusedIcon,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = navigationItem.label,


                    )
            },
            selected = isSelected,
            onClick = onClick,
        )
    }


}

@Composable
fun <T> ItemDecorator(
    navigationItem: NavigationItemInfo<T>,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    NavigationDrawerItem(
        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
        icon = {
            Icon(
                navigationItem.unFocusedIcon,
                contentDescription = null
            )
        },
        label = {
            Text(
                text = navigationItem.label,


                )
        },
        selected = isSelected,
        onClick = onClick,
    )

}
