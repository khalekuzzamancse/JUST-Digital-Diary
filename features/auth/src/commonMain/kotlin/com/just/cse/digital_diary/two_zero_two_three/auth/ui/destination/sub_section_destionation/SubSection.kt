package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destination.sub_section_destionation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import com.just.cse.digital_diary.two_zero_two_three.auth.data.repository.FacultyInfo
import com.just.cse.digital_diary.two_zero_two_three.auth.data.repository.SectionRepository
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.NavigationItem
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destination.home_screen.drawer.ScreenWithModalDrawer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SubSectionViewModel(
    private val subSections: List<FacultyInfo>,
    val navigateToSubSection: (String) -> Unit = {},
) {
    private val repository = SectionRepository
    private val _selectedIndex = MutableStateFlow(-1)
    val selectedSectionIndex = _selectedIndex.asStateFlow()
    private val _sections = MutableStateFlow(subSections.map {
        NavigationItem(
            label = it.name,
            icon = it.logo,
            key = it.id
        )
    })

    val section = _sections.asStateFlow()

    fun onSectionChildSelected(index: Int) {
        _selectedIndex.value = index
        val destination=_sections.value.getOrNull(index)
        if(destination!=null){
            val subSectionId=destination.key
            navigateToSubSection(subSectionId)
        }

    }


}

@Composable
fun SectionChild(
    subSections: List<FacultyInfo>,
    navigateToSectionChild: (String) -> Unit,//index
    content: @Composable () -> Unit = {},
) {
    val viewModel = remember {
        SubSectionViewModel(
            subSections,
            navigateToSubSection = {
                navigateToSectionChild(it)
            }
        )
    }
    SectionChild(
        subSection = viewModel.section.collectAsState().value,
        selectedSubSectionIndex = viewModel.selectedSectionIndex.collectAsState().value,
        onSubSectionSelected = viewModel::onSectionChildSelected,
        content = content
    )

}


@Composable
fun SectionChild(
    subSection: List<NavigationItem<String>>,
    selectedSubSectionIndex: Int,
    onSubSectionSelected: (Int) -> Unit,
    content: @Composable () -> Unit = {},
) {
    ScreenWithModalDrawer(
        destinations = subSection,
        selectedDesertionIndex = selectedSubSectionIndex,
        onDestinationSelected = onSubSectionSelected,
        content = content
    )

}