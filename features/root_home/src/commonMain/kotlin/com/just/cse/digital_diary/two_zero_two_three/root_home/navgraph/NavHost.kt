package com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.just.cse.digital_diary.features.common_ui.navigation.modal_drawer.AnimateVisibilityDecorator
import com.just.cse.digital_diary.features.common_ui.navigation.modal_drawer.ModalDrawerDecorator
import com.just.cse.digital_diary.features.faculty.faculty.navigation.FacultyModule
import com.just.cse.digital_diary.two_zero_two_three.root_home.ui.about_us.AboutUs
import com.just.cse.digital_diary.two_zero_two_three.root_home.ui.home.RootHomeContent
import com.just.cse.digital_diary.two_zero_two_three.root_home.ui.message_from_vc.ViceChancellorMessage
import com.just.cse.digital_diary.two_zero_two_three.root_home.ui.share_note.CreateNoteScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TopMostDestinationViewModel(val navigateToSection: (String) -> Unit = {}) {
    private val _selectedIndex = MutableStateFlow(0)
    val selectedSectionIndex = _selectedIndex.asStateFlow()
    fun onSectionSelected(index: Int) {
        _selectedIndex.value = index
    }


}


class RootNavHost : Screen {
    @Composable
    override fun Content() {
        val navigator= LocalNavigator.current
        val viewModel = remember { TopMostDestinationViewModel() }
        val currentDestinationIndex=viewModel.selectedSectionIndex.collectAsState().value
        ModalDrawerDecorator(
            destinations = topMostDestinations,
            selectedDesertionIndex =currentDestinationIndex ,
            onDestinationSelected = {selectedDestinationIndex->
                viewModel.onSectionSelected(selectedDestinationIndex)
                if(navigator!=null){
                    navigationManager(
                        navigator=navigator,
                        selectedIndex=selectedDestinationIndex
                    )
                }

            },
            content = {
                    when (currentDestinationIndex){
                        RootDestinations.HOME->{
                            AnimateVisibilityDecorator {
                                RootHomeContent(
                                    onCreateNote = {
                                        navigator?.push(CreateNoteScreen())
                                    }
                                )
                            }

                        }
                        RootDestinations.MESSAGE_FROM_VC ->{
                            AnimateVisibilityDecorator {
                                ViceChancellorMessage()
                            }
                        }
                        RootDestinations.ABOUT_US->{
                            AnimateVisibilityDecorator {
                                AboutUs()
                            }


                    }
                }
            }
        )

    }
}
/*
These destinations will contains the separator navigation component of them that is why
we do not pass them as  a content of drawer decorator,
 */
private fun navigationManager(navigator:Navigator, selectedIndex:Int){
    when (selectedIndex){
        RootDestinations.FACULTY_MEMBERS->{
          navigator.push(FacultyModuleRoot())
        }

    }

}


