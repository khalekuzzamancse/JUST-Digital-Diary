package com.just.cse.digital_diary.features.faculty.destination

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.features.faculty.faculty.destination.teacher_list.TeacherList
import com.just.cse.digitaldiary.twozerotwothree.core.di.faculty.FacultyComponentProvider

@Composable
fun TeacherListDestination(
    deptId: String,
    onExitRequest:()->Unit={},
) {
    TeacherList(
        deptId = deptId,
        repository = FacultyComponentProvider.getTeacherListRepository(),
        onExitRequest=onExitRequest
    )
}