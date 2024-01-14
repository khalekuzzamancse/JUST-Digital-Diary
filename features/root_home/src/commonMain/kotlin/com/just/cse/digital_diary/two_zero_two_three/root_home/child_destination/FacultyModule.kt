package com.just.cse.digital_diary.two_zero_two_three.root_home.child_destination

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.just.cse.digital_diary.features.common_ui.navigation.modal_drawer.AnimateVisibilityDecorator
import com.just.cse.digital_diary.features.faculty.faculty.navigation.FacultyModuleEntryPoint


class FacultyModule(
    private val onExitRequested:()->Unit,
): Screen {
    @Composable
    override fun Content() {
        AnimateVisibilityDecorator {
            FacultyModuleEntryPoint(
                onExitRequested=onExitRequested
            )
        }
    }

}

