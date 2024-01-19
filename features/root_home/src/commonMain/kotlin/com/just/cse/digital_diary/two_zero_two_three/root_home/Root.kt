package com.just.cse.digital_diary.two_zero_two_three.root_home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.auth.AuthModuleEntryPoint
import com.just.cse.digital_diary.two_zero_two_three.common_ui.list.AdaptiveList
import com.just.cse.digital_diary.two_zero_two_three.department.DepartmentModuleEntryPoint
import com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.RootNavGraph
import com.just.cse.digital_diary.two_zero_two_three.root_home.ui.themes.AppTheme
import com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.local_destinations.notes_screen.NotesScreen
import com.just.cse.digitaldiary.twozerotwothree.data.repository.department_info.DepartmentInfoRepository


@Composable
fun RootModule() {
    AppTheme {
        NotesScreen()
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

@Composable
fun F() {

}