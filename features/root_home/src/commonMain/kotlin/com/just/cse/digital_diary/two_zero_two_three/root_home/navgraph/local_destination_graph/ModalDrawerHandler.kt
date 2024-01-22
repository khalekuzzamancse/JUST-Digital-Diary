package com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.local_destination_graph

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ModalDrawerHandler {
    private val _selectedIndex = MutableStateFlow(0)
    val selectedSectionIndex = _selectedIndex.asStateFlow()

    fun onSectionSelected(index: Int) {
        _selectedIndex.value = index
        closeDrawer()

    }
    private val _drawerState = MutableStateFlow(DrawerState(DrawerValue.Closed))
    val drawerState = _drawerState.asStateFlow()
    fun openDrawer(){
       _drawerState.update { DrawerState(DrawerValue.Open) }
    }
    fun closeDrawer(){
        _drawerState.update { DrawerState(DrawerValue.Closed) }
    }



}
