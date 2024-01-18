package com.just.cse.digital_diary.two_zero_two_three.department.local_destinations.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item.NavigationItem
import com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item.NavigationItemProps
import com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item.NavigationItemInfo2
import com.just.cse.digital_diary.two_zero_two_three.department.local_destinations.employees.AdaptiveEmployeeList
import com.just.cse.digitaldiary.twozerotwothree.data.repository.repository.Employee


@Composable
internal fun NonCompactScreenLayout(
    selectedDestinationIndex: Int,
    employees: List<Employee>? = null,
    onEmployeeListRequest: (Int) -> Unit,
    onEmployeeListDismissRequest: () -> Unit,
    homeContent: (@Composable () -> Unit)? = null,
) {
    val destinations = listOf(
        NavigationItemInfo2(
            key = "1",
            label = "Employee",
            iconText = "EMP"
        ),
        NavigationItemInfo2(
            key = "1",
            label = "Staff",
            iconText = "STF"
        )
    )
    Row(modifier = Modifier.fillMaxWidth()) {
        NavigationSection(
            modifier = Modifier,
            destinations = destinations,
            onDestinationSelected = {
                onEmployeeListRequest(it)
            },
            selectedDestinationIndex = selectedDestinationIndex,
        )
        Spacer(Modifier.width(16.dp))
        Box(Modifier.weight(1f)) {
            //do not recompose the content so that it not again animated when back from department list
            if (homeContent != null) {
                homeContent()
            }

            AnimatedContent(
                modifier = Modifier,
                targetState = employees,
                transitionSpec = {
                    slideIntoContainer(
                        animationSpec = tween(durationMillis = 300, easing = EaseIn),
                        towards = AnimatedContentTransitionScope.SlideDirection.Start
                    ).togetherWith(
                        slideOutOfContainer(
                            animationSpec = tween(durationMillis = 300, easing = EaseIn),
                            towards = AnimatedContentTransitionScope.SlideDirection.Up
                        )
                    )
                }
            ) { employees ->
                if (employees != null) {
                    EmployeeList(
                        modifier = Modifier.fillMaxSize(),
                        onDismissRequest = onEmployeeListDismissRequest,
                        employees = employees
                    )


                }


            }

        }


    }
}

@Composable
private fun EmployeeList(
    modifier: Modifier = Modifier,
    employees: List<Employee>,
    title: String? = null,
    enableBackNavigation: Boolean = true,
    onDismissRequest: () -> Unit = {},
) {
    Surface(
        shadowElevation = 8.dp
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(Modifier, verticalAlignment = Alignment.CenterVertically) {
                AnimatedVisibility(enableBackNavigation) {
                    IconButton(
                        onClick = onDismissRequest
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )

                    }
                }
                if (title != null) {
                    Box(Modifier) {
                        Text(text = title)
                    }
                }

            }
            AdaptiveEmployeeList(
                modifier = Modifier.wrapContentWidth(),
                employees = employees
            )


        }
    }


}


@Composable
private fun NavigationSection(
    modifier: Modifier = Modifier,
    destinations: List<NavigationItemInfo2<String>>,
    onDestinationSelected: (Int) -> Unit,
    selectedDestinationIndex: Int,
) {

    Column(modifier.fillMaxHeight().
    background(MaterialTheme.colorScheme.surfaceColorAtElevation(16.dp))) {
        IconButton(
            onClick = {}
        ){
            Icon(
                imageVector = Icons.Default.ExitToApp,
                contentDescription = null
            )

        }
        VerticalListNavigation(
            modifier = Modifier,
            destinations = destinations,
            onDestinationSelected = onDestinationSelected,
            selectedDestinationIndex = selectedDestinationIndex,
            colors = NavigationItemProps(
                focusedColor = MaterialTheme.colorScheme.secondary,
                unFocusedColor = MaterialTheme.colorScheme.secondaryContainer,
            )
        )
    }



}

@Composable
private fun VerticalListNavigation(
    modifier: Modifier = Modifier,
    destinations: List<NavigationItemInfo2<String>>,
    onDestinationSelected: (Int) -> Unit,
    selectedDestinationIndex: Int,
    colors: NavigationItemProps = NavigationItemProps(
        focusedColor = MaterialTheme.colorScheme.errorContainer,
        unFocusedColor = MaterialTheme.colorScheme.primaryContainer,
    )
) {
    Column(
        modifier = modifier
            .width(IntrinsicSize.Max)
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        destinations.forEachIndexed { index, _ ->
            NavigationItem(
                modifier = Modifier.fillMaxWidth(),
                navigationItem = destinations[index],
                visibilityDelay = (index + 1) * 10L,
                selected = selectedDestinationIndex == index,
                onClick = {
                    onDestinationSelected(index)
                },
                onFocusing = {

                },
                colors = colors
            )
        }

    }

}

