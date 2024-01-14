package com.just.cse.digital_diary.features.departments.navgraph.local_destinations.home

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class HomeViewModel {
    private val _selectedIndex = MutableStateFlow(-1)
    val selectedSectionIndex = _selectedIndex.asStateFlow()
    fun onSectionSelected(index: Int) {
        _selectedIndex.value = index
    }

}
