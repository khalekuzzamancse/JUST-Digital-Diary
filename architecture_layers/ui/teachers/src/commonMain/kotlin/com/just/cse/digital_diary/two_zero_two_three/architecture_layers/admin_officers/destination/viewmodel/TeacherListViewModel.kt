package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.admin_officers.destination.viewmodel

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.teachers.model.TeacherListResponseModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.teachers.repoisitory.TeacherListRepository
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.admin_officers.component.employee_list.state.Teacher
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.admin_officers.destination.event.TeachersDestinationEvent
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.admin_officers.destination.state.TeacherListDestinationState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TeacherListViewModel (
   private val repository: TeacherListRepository
){
    private val _uiState= MutableStateFlow(TeacherListDestinationState())
    val uiState =_uiState.asStateFlow()
    private val departmentId=MutableStateFlow<String?>(null)

    fun onEvent(event:TeachersDestinationEvent){

    }
    fun setDepartmentId(id:String){
        departmentId.update { id }
        updateTeacherList()

    }
    private fun updateTeacherList(){
        CoroutineScope(Dispatchers.Default).launch {
            startLoading()
            departmentId.value?.let {departmentId->
                val result =repository.getTeachers(departmentId)
                if(result is TeacherListResponseModel.Success){
                    _uiState.update {uiState->
                        val   teacherListState=uiState.teacherListState.copy(teachers =result.data.map {model->
                            Teacher(
                                name = model.name,
                                email=model.email,
                                additionalEmail = model.additionalEmail,
                                phone = model.phone,
                                achievements = model.achievements,
                                designations = model.designations,
                                deptName = model.deptName,
                                deptSortName = model.deptSortName,
                                roomNo = model.roomNo
                            )
                        } )
                        uiState.copy(
                            teacherListState =teacherListState
                        )

                    }
                }


            }
            stopLoading()
        }

    }
    private fun startLoading(){
        _uiState.update { it.copy(isLoading = true) }
    }
    private fun stopLoading(){
        _uiState.update { it.copy(isLoading = false) }
    }
}