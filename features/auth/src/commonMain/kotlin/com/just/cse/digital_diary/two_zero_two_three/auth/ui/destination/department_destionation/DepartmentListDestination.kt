package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destination.department_destionation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import com.just.cse.digital_diary.features.common_ui.navigation.NavigationItem
import com.just.cse.digital_diary.features.common_ui.navigation.modal_drawer.ModalDrawerDecorator
import com.just.cse.digital_diary.two_zero_two_three.auth.data.repository.Department
import com.just.cse.digital_diary.two_zero_two_three.auth.data.repository.SectionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DepartmentListViewModel(
    private val departments:List<Department>,
    val navigateToDepartment: (String) -> Unit = {},
) {
    private val repository = SectionRepository
    private val _selectedIndex = MutableStateFlow(-1)
    val selectedSectionIndex = _selectedIndex.asStateFlow()
    private val _sections = MutableStateFlow(
        departments.map {
            NavigationItem(
                label = it.shortName,
                unFocusedIcon = it.logo,
                key = it.id
            )
        }
    )

    val section = _sections.asStateFlow()
    fun onDeptSelected(index: Int) {
        _selectedIndex.value = index
        val destination=_sections.value.getOrNull(index)
        if(destination!=null){
            val deptId=destination.key
            navigateToDepartment(deptId)
        }
    }

}

@Composable
fun DepartmentListDestination(
    departments: List<Department>,
    navigateToDepartment: (String) -> Unit,//index
    content: @Composable () -> Unit = {},
) {
    val viewModel = remember {
        DepartmentListViewModel(
            departments,
            navigateToDepartment = navigateToDepartment

        )
    }
    DepartmentListDestination(
        subSection = viewModel.section.collectAsState().value,
        selectedSubSectionIndex = viewModel.selectedSectionIndex.collectAsState().value,
        onSubSectionSelected = viewModel::onDeptSelected,
        content = content
    )

}


@Composable
fun DepartmentListDestination(
    subSection: List<NavigationItem<String>>,
    selectedSubSectionIndex: Int,
    onSubSectionSelected: (Int) -> Unit,
    content: @Composable () -> Unit = {},
) {

    ModalDrawerDecorator(
        destinations = subSection,
        selectedDesertionIndex = selectedSubSectionIndex,
        onDestinationSelected = onSubSectionSelected,
        content = content
    )

}