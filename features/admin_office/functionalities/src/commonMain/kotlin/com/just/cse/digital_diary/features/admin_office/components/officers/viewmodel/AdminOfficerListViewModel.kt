package com.just.cse.digital_diary.features.admin_office.components.officers.viewmodel

import com.just.cse.digital_diary.features.admin_office.components.officers.state.AdminOfficerListDestinationState
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.admin_officers.component.employee_list.state.AdminOfficer
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.model.AdminOfficersListResponseModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.repoisitory.AdminOfficerListRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AdminOfficerListViewModel(
    private val repository: AdminOfficerListRepository,
    private val subOfficeId: String,
) {
    private val _uiState = MutableStateFlow(AdminOfficerListDestinationState())
    val uiState = _uiState.asStateFlow()
    suspend fun updateTeacherList() {
        startLoading()
        val result = repository.getOfficers(subOfficeId)
        if (result is AdminOfficersListResponseModel.Success) {
            _uiState.update { uiState ->
                val adminOfficerListState =
                    uiState.adminOfficerListState.copy(adminOfficers = result.data.map { model ->
                        AdminOfficer(
                            name = model.name,
                            email = model.email,
                            additionalEmail = model.additionalEmail,
                            phone = model.phone ?: "",
                            achievements = model.achievements,
                            designations = model.designations,
                            roomNo = model.roomNo
                        )
                    })
                uiState.copy(
                    adminOfficerListState = adminOfficerListState
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