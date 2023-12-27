package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destination.top_most_home_destination

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import com.just.cse.digital_diary.two_zero_two_three.auth.data.repository.SectionRepository
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.NavigationItem
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.common.modal_drawer.ModalDrawerDecorator
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destination.top_most_home_destination.nav_graph.topMostDestinations
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/*
selected group
selected items
on group selection
on item selection

It responsible for telling which group ,which item is clicked.
it takes a drawer list and maintains it,tell the client is is interested to update
the list or not.
 */

/*
It is UI independent,it does not know what will be do with this data,
it just can provide data as a database kind of thing..

 */


class HomeScreenViewModel(
    val navigateToSection: (String) -> Unit = {},
) {
    private val repository = SectionRepository
    private val _selectedIndex = MutableStateFlow(-1)
    val selectedSectionIndex = _selectedIndex.asStateFlow()
    private val _sections = MutableStateFlow(topMostDestinations)


    val section = _sections.asStateFlow()



    fun onSectionSelected(index: Int) {
        _selectedIndex.value = index
        val destination = _sections.value.getOrNull(index)
        if (destination != null) {
            val sectionId = destination.key
            navigateToSection(sectionId)
        }
    }

}

@Composable
fun HomeScreen(
    navigateToSection: (String) -> Unit,//index
    content: @Composable () -> Unit = {},
) {
    val viewModel = remember {
        HomeScreenViewModel(
            navigateToSection = {
                navigateToSection(it)
            }
        )
    }
    HomeScreen(
        sections = viewModel.section.collectAsState().value,
        selectedSectionIndex = viewModel.selectedSectionIndex.collectAsState().value,
        onSectionSelected = viewModel::onSectionSelected,
        content = content
    )

}

@Composable
fun HomeScreen(
    sections: List<NavigationItem<String>>,
    selectedSectionIndex: Int,
    onSectionSelected: (Int) -> Unit,
    content: @Composable () -> Unit = {},
) {

    ModalDrawerDecorator(
        destinations = sections,
        selectedDesertionIndex = selectedSectionIndex,
        onDestinationSelected = onSectionSelected,
        content = content
    )

}

