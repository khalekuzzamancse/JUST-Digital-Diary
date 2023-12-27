package com.just.cse.digital_diary.features.faculty.faculty.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.just.cse.digital_diary.features.common_ui.navigation.modal_drawer.ModalDrawerDecorator
import com.just.cse.digital_diary.two_zero_two_three.department.department_list.DepartmentListDestination
import com.just.cse.digitaldiary.twozerotwothree.data.data.repository.DepartmentFakeDB
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class TopMostDestinationViewModel {
    private val _selectedIndex = MutableStateFlow(-1)
    val selectedSectionIndex = _selectedIndex.asStateFlow()
    fun onSectionSelected(index: Int) {
        _selectedIndex.value = index
    }

}


class FacultyModuleNavHost : Screen {
    var navigator:Navigator?=null
    @Composable
    override fun Content() {
        navigator= LocalNavigator.current

        val viewModel = remember { TopMostDestinationViewModel() }
        val currentDestinationIndex=viewModel.selectedSectionIndex.collectAsState().value
        ModalDrawerDecorator(
            destinations = destinations,
            selectedDesertionIndex =currentDestinationIndex ,
            onDestinationSelected = {
                viewModel.onSectionSelected(it)
                when(it){
                    0->{
                        navigator?.push(DepartmentDestinations())
                    }
                }
            }
        ){
            Text("This is department list")

        }
    }
}
class DepartmentDestinations:Screen{
    @Composable
    override fun Content() {
        DepartmentListDestination(
            departments = DepartmentFakeDB.departmentsOfEngineeringAndTechnology
        )
    }

}
