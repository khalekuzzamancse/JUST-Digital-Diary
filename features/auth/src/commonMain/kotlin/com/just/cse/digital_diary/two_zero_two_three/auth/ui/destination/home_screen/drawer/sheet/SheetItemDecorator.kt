package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destination.home_screen.drawer.sheet

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.NavigationItem

@Composable
fun <T>ItemDecorator(
    navigationItem: NavigationItem<T>,
    isSelected:Boolean,
    onClick:() -> Unit,
) {
    NavigationDrawerItem(
        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
        icon = {
            Icon(
                navigationItem.icon,
                contentDescription = null
            )
        },
        label = {
            Text(
                text = navigationItem.label,


                )
        },
        selected = isSelected ,
        onClick = onClick,
    )

}