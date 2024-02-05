package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.teachers.destination.viewmodel

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.teachers.model.TeacherListResponseModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.teachers.repoisitory.TeacherListRepository
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.teachers.employee_list.state.Teacher
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.teachers.destination.state.TeacherListDestinationState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TeacherListViewModel(
    private val repository: TeacherListRepository,
    private val deptId: String
) {
    private val _uiState = MutableStateFlow(TeacherListDestinationState())
    val uiState = _uiState.asStateFlow()

    suspend fun loadTeacherList() {
        startLoading()
        val result = repository.getTeachers(deptId)
        if (result is TeacherListResponseModel.Success) {
            _uiState.update { uiState ->
                val teacherListState =
                    uiState.teacherListState.copy(teachers = result.data.map { model ->
                        Teacher(
                            name = model.name,
                            email = model.email,
                            additionalEmail = model.additionalEmail,
                            phone = model.phone,
                            achievements = model.achievements,
                            designations = model.designations,
                            deptName = model.deptName,
                            deptSortName = model.deptSortName,
                            roomNo = model.roomNo
                        )
                    })
                uiState.copy(
                    teacherListState = teacherListState
                )

            }
        }
        stopLoading()
    }
    private fun startLoading() {
        _uiState.update { it.copy(isLoading = true) }
    }

    private fun stopLoading() {
        _uiState.update { it.copy(isLoading = false) }
    }

}




