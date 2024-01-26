package com.just.cse.digital_diary.two_zero_two_three.department.destinations.state

import com.just.cse.digital_diary.two_zero_two_three.department.component.state.TopNBottomBarDecoratorState

data class DepartmentInfoScreenState(
    val isLoading: Boolean = false,
    val message: String?=null,
    val decoratorState: TopNBottomBarDecoratorState = TopNBottomBarDecoratorState()
)
