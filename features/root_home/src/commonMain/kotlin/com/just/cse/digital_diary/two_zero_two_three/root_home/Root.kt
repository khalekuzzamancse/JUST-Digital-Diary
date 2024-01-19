package com.just.cse.digital_diary.two_zero_two_three.root_home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.features.faculty.faculty.navigation.FacultyModuleEntryPoint
import com.just.cse.digital_diary.two_zero_two_three.root_home.ui.themes.AppTheme

@Composable
fun RootModule() {
    AppTheme {
        FacultyModuleEntryPoint({})
//        var loginSuccess by remember {
//            mutableStateOf(false)
//        }
//        if (loginSuccess) {
//            RootNavGraph()
//        } else {
//            AuthModuleEntryPoint(
//                onLoginSuccess = {
//                    loginSuccess = true
//                }
//            )
//        }

 }



}


/**
 * A [Component] that allows for fast scrolling of content by dragging its thumb.
 * Its thumb disappears when the scrolling container is dormant.
 * @param modifier a [Modifier] for the [Scrollbar]
 * @param state the driving state for the [Scrollbar]
 * @param orientation the orientation of the scrollbar
 * @param onThumbMoved the fast scroll implementation
 */

