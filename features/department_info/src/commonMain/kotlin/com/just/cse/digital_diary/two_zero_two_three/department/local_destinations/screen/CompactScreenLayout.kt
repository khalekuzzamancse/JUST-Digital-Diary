package com.just.cse.digital_diary.two_zero_two_three.department.local_destinations.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.department.common.TopNBottomBarDecorator
import com.just.cse.digital_diary.two_zero_two_three.department.local_destinations.navigation_items.bottomNavigationItems
import com.just.cse.digital_diary.two_zero_two_three.employee_list.employee_list.EmployeeList
import com.just.cse.digitaldiary.twozerotwothree.data.repository.repository.Employee


@Composable
internal fun CompactScreenLayout(
    homeContent:( @Composable ()->Unit ),
    destinationTitle:String,
    selectedDestinationIndex: Int,
    employees: List<Employee>? = null,
    onEmployeeListRequest: (Int) -> Unit,
    onExitRequested:()->Unit,
) {

    TopNBottomBarDecorator(
        topBarTitle = destinationTitle,
        topNavigationIcon = if (selectedDestinationIndex <=0) Icons.Outlined.ArrowBack else null,
        onNavigationIconClick = onExitRequested,
        bottomDestinations = bottomNavigationItems,
        selectedDestinationIndex = selectedDestinationIndex,
        onDestinationSelected = onEmployeeListRequest
    ) { modifier ->
        AnimatedContent(
            modifier = modifier,
            targetState = selectedDestinationIndex,
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
        ){selectedDestination->
            when (selectedDestination) {
                1,2->{
                   if (employees != null) {
                       EmployeeList(
                           employees=employees,
                           onCallRequest = {},
                           onEmailRequest = {}
                       ) {}
                   }
               }

                else -> {
                    homeContent()
                }
            }
        }



    }
}
