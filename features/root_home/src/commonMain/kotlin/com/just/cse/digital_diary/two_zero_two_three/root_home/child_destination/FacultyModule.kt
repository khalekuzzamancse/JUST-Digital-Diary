package com.just.cse.digital_diary.two_zero_two_three.root_home.child_destination

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.just.cse.digital_diary.features.faculty.faculty.FacultyModuleEntryPoint
import com.just.cse.digital_diary.features.faculty.faculty.FacultyModuleEvent


class FacultyModule(
    private val event: FacultyModuleEvent,
    private val onExitRequested:()->Unit,
): Screen {
    @Composable
    override fun Content() {
        FacultyModuleEntryPoint(
            event=event,
            onExitRequested=onExitRequested
        )
//        AnimateVisibilityDecorator {
//
//        }
    }

}

