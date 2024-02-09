package com.just.cse.digital_diary.features.faculty.destination.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.features.faculty.components.event.FacultyEvent
import com.just.cse.digital_diary.features.faculty.components.functionalities.teacher_list.TeacherList
import com.just.cse.digital_diary.two_zero_two_three.common_ui.TopBarDecorator
import com.just.cse.digitaldiary.twozerotwothree.core.di.faculty.FacultyComponentProvider

@Composable
internal fun TeachersScreen(
    deptId: String,
    onExitRequest: () -> Unit,
    onEvent: (FacultyEvent)->Unit,
    ) {
    TopBarDecorator(
        topNavigationIcon = Icons.AutoMirrored.Default.ArrowBack,
        onNavigationIconClick = onExitRequest,
        topBarTitle = "Teacher List"
    ) {
        TeacherList(
            modifier = Modifier.padding(it),
            deptId = deptId,
            repository = FacultyComponentProvider.getTeacherListRepository(),
            onEvent=onEvent
        )
    }

}