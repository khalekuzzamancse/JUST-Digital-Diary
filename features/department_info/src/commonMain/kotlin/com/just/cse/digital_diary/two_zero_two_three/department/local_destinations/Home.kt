package com.just.cse.digital_diary.two_zero_two_three.department.local_destinations

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.just.cse.digitaldiary.twozerotwothree.data.repository.department_info.DepartmentInfoRepository

@Composable
internal fun Home(
    modifier: Modifier,

) {
    val message= DepartmentInfoRepository.getDepartmentMessage("1")
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        DepartmentFromChairmen(
            imageURL =message.deptImageUrl ,
            message=message.message
        )

    }

}

