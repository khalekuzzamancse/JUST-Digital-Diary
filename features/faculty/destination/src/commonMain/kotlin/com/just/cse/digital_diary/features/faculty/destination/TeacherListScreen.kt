package com.just.cse.digital_diary.features.faculty.destination

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.features.faculty.faculty.destination.teacher_list.TeacherListScreen
import com.just.cse.digitaldiary.twozerotwothree.core.di.faculty.FacultyComponentProvider

@Composable
fun TeacherListScreen(
    deptId: String
) {
    TeacherListScreen(
        repository = FacultyComponentProvider.getTeacherListRepository(),
        deptId = deptId
    )
}