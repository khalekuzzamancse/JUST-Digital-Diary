package com.just.cse.digital_diary.features.admin_office.offices

import com.just.cse.digitaldiary.twozerotwothree.data.repository.admin_office_repository.data.AdminOfficeRepository
import com.just.cse.digitaldiary.twozerotwothree.data.repository.admin_office_repository.data.AdminOfficeSubOfficeRepository
import com.just.cse.digitaldiary.twozerotwothree.data.repository.admin_office_repository.model.AdminOfficeSubOffice
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class ViewModel(
    private val onSubOfficeNavigationRequest: (String) -> Unit,
) {
    val scope= CoroutineScope(Dispatchers.Default)
    val offices = AdminOfficeRepository().offices
    private val _selectedOfficeSubOffices = MutableStateFlow<List<AdminOfficeSubOffice>>(emptyList())
    val selectedOfficeSubOffices = _selectedOfficeSubOffices.asStateFlow()
    private val _selectedOffice = MutableStateFlow(0)
    val selectedOffice = _selectedOffice.asStateFlow()
    private val _selectedSubOffice = MutableStateFlow(0)
    val selectedSubOffice = _selectedSubOffice.asStateFlow()


    fun onOfficeSelected(index: Int) {
        _selectedOffice.update { index }
        scope.launch {
            _selectedOfficeSubOffices.update { AdminOfficeSubOfficeRepository().getSubOffice("01")}
        }

    }

    fun onSubOfficeSelected(index: Int) {
        _selectedSubOffice.update { index }
        onSubOfficeNavigationRequest(
            _selectedOfficeSubOffices.value[index].id
        )

    }


    fun onClearSubOfficeSelection() {
        _selectedSubOffice.update { 0 }
        _selectedOfficeSubOffices.update { emptyList() }
    }
}

