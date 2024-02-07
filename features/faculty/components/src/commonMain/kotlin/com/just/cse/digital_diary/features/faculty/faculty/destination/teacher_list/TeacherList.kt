package com.just.cse.digital_diary.features.faculty.faculty.destination.teacher_list

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.teachers.repoisitory.TeacherListRepository
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.teachers.destination.TeacherListDestination
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.teachers.destination.event.TeachersDestinationEvent
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.teachers.destination.viewmodel.TeacherListViewModel

@Composable
 fun TeacherList(
    deptId: String,
    repository: TeacherListRepository,
    onExitRequest:()->Unit={},
) {
    TeacherListDestination(
        modifier = Modifier,
        viewModel = TeacherListViewModel(
            repository = repository,
            deptId = deptId
        ),
        onEvent = {event ->
            when(event){
                TeachersDestinationEvent.ExitRequest->{
                    onExitRequest()
                }
            }

        }
    )
}