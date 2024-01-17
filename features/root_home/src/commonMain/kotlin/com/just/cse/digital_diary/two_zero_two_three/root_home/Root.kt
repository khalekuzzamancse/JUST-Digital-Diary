package com.just.cse.digital_diary.two_zero_two_three.root_home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.features.faculty.faculty.navigation.local_destinations.faculties.FacultiesScreenNonExpanded
import com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item.NavigationItemInfo2
import com.just.cse.digital_diary.two_zero_two_three.common_ui.side_sheet.SideSheet
import com.just.cse.digital_diary.two_zero_two_three.common_ui.side_sheet.SideSheetNavigation
import com.just.cse.digital_diary.two_zero_two_three.root_home.ui.themes.AppTheme
import com.just.cse.digitaldiary.twozerotwothree.data.repository.repository.FacultyRepository


@Composable
fun RootModule() {
    AppTheme {
        FacultiesScreenNonExpanded()
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


