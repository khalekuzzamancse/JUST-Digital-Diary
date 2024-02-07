package com.just.cse.digital_diary.features.faculty.destination.screens

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.just.cse.digital_diary.features.faculty.components.functionalities.teacher_list.TeacherList
import com.just.cse.digitaldiary.twozerotwothree.core.di.faculty.FacultyComponentProvider


internal class TeacherListScreen(
    private val deptId: String,
    private val exitRequest:()->Unit
) : Screen {
    @Composable
    override fun Content() {
        TeacherList(
            deptId = deptId,
            repository = FacultyComponentProvider.getTeacherListRepository(),
        )

    }
}