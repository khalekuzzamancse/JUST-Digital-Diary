package com.just.cse.digital_diary.two_zero_two_three.department.destinations.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.teachers.repoisitory.TeacherListRepositoryImpl
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.teachers.destination.viewmodel.TeacherListViewModel
import com.just.cse.digital_diary.two_zero_two_three.department.component.event.DepartmentInfoNavigationEvent
import com.just.cse.digital_diary.two_zero_two_three.department.component.state.TopNBottomBarDecoratorState
import com.just.cse.digital_diary.two_zero_two_three.department.destinations.event.DepartmentInfoEvent
import com.just.cse.digital_diary.two_zero_two_three.department.destinations.state.DepartmentInfoScreenState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class ViewModel(
    private val departmentId: String
) {

    private val _uiState = MutableStateFlow(DepartmentInfoScreenState())
    val uiState = _uiState.asStateFlow()
    val teacherListViewModel = TeacherListViewModel(TeacherListRepositoryImpl())
    fun onEvent(event: DepartmentInfoEvent) {
        when (event) {
            is DepartmentInfoNavigationEvent.DestinationSelected -> onDestinationChangedRequest(
                event.index
            )
        }
    }
    init {
        observeTeacherListLoading()
    }
    private fun observeTeacherListLoading(){
        CoroutineScope(Dispatchers.Default).launch {
            teacherListViewModel.uiState.collect{teacherListState->
                _uiState.update {
                    it.copy(isLoading = teacherListState.isLoading)
                }

            }
        }
    }

    private fun onDestinationChangedRequest(index: Int) {

        val navigationIcon = if (index == 0) Icons.Default.Menu else null
        val title = if (index == 0) "Home" else "Teachers"
        val decoratorState=_uiState.value.decoratorState.copy(
            selectedDestination = index,
            topBarTitle = title,
            topNavigationIcon = navigationIcon

        )
        _uiState.update {
            it.copy(
                decoratorState=decoratorState
            )
        }
        if(index==1){
            teacherListViewModel.setDepartmentId(departmentId)
        }
    }


}