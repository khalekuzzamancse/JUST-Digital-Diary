package com.just.cse.digital_diary.two_zero_two_three.department.destinations.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.teachers.destination.viewmodel.TeacherListViewModel
import com.just.cse.digital_diary.two_zero_two_three.department.component.event.DepartmentInfoNavigationEvent
import com.just.cse.digital_diary.two_zero_two_three.department.component.TopNBottomBarDecorator
import com.just.cse.digital_diary.two_zero_two_three.department.component.state.TopNBottomBarDecoratorState
import com.just.cse.digital_diary.two_zero_two_three.department.destinations.event.DepartmentInfoEvent
import com.just.cse.digital_diary.two_zero_two_three.department.destinations.teacher_list.TeacherList


@Composable
internal fun CompactScreenLayout(
    decoratorState: TopNBottomBarDecoratorState,
    teacherListViewModel: TeacherListViewModel,
    onEvent: (DepartmentInfoEvent) -> Unit,
    homeContent: (@Composable () -> Unit),
) {
    TopNBottomBarDecorator(
        state = decoratorState,
        onNavigationIconClick = {
            onEvent(DepartmentInfoEvent.ExitRequest)
        },
        onDestinationSelected = {
            onEvent(DepartmentInfoNavigationEvent.DestinationSelected(it))
        }
    ) { modifier ->
        AnimatedContent(
            modifier = modifier,
            targetState = decoratorState.selectedDestination,
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
        ) { selectedDestination ->
            when (selectedDestination) {
                1 -> {
                    TeacherList(
                        viewModel =teacherListViewModel
                    )
                }
                0 -> {
                    homeContent()
                }
            }
        }


    }
}
