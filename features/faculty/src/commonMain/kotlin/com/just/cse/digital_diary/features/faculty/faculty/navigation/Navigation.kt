package com.just.cse.digital_diary.features.faculty.faculty.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.just.cse.digital_diary.two_zero_two_three.auth.data.repository.SectionRepository
import com.just.cse.digitaldiary.twozerotwothree.data.data.repository.FacultyRepository



class DepartmentInfo(
    val onNavigateToBack:()->Unit,
    val deptId:String,
):Screen{
    @Composable
    override fun Content() {
//        DepartmentNavGraph(
//            departmentId = deptId,
//            onNavigationIconClick = onNavigateToBack
//        )
    }

}

