package common.ui.navigation.modal_drawer.sheet.item

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.ui.navigation.modal_drawer.sheet.NavItem

import kotlinx.coroutines.delay

/**
 * Non-default parameters: [item], [isSelected], [onClick]
 *  * has a distinct composable for drawer items without animation.
 *  * Handling both animated and non-animated scenarios within the same composable may inadvertently invoke the Animation API unnecessarily, leading to unwanted effects as the animation API executes on every frame.
 *  * It is crucial to exercise caution when dealing with animation APIs to avoid unintended calls or accidental object creation within the animation API, preventing unnecessary object creation.
 * */
@Composable
fun DrawerItemDecorator(
    item: NavItem,
    isSelected: Boolean,
    onClick: () -> Unit,
    visibilityDelay: Long? = null,
) {


    if (visibilityDelay == null) //no visibility==no animation
    {
        AnimationLessDecorator(
            navigationItem = item,
            isSelected = isSelected,
            onClick =onClick
        )
    } else {
        DecoratorWithAnimation(
            navigationItem = item,
            isSelected = isSelected,
            visibilityDelay = visibilityDelay,
            onClick = onClick
        )
    }
}

@Composable
private fun AnimationLessDecorator(
    navigationItem: NavItem,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    NavigationDrawerItem(
        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
        icon = {
            Icon(
                navigationItem.unselectedIcon,
                contentDescription = null
            )
        },
        label = {
            Text(
                text = navigationItem.label
            )
        },
        selected = isSelected,
        onClick = onClick,
    )


}

@Composable
private fun DecoratorWithAnimation(
    navigationItem: NavItem,
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
        visible = visible
    ) {
        NavigationDrawerItem(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp, start = 4.dp, end = 4.dp),
            icon = {
                Icon(
                    navigationItem.unselectedIcon,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = navigationItem.label
                )
            },
            selected = isSelected,
            onClick = onClick,
            colors = NavigationDrawerItemDefaults.colors(
                selectedContainerColor = MaterialTheme.colorScheme.secondary,
                selectedTextColor =  MaterialTheme.colorScheme.onSecondary,
                selectedIconColor =  MaterialTheme.colorScheme.onSecondary,
                unselectedIconColor = MaterialTheme.colorScheme.primary,

            )
        )
    }
}