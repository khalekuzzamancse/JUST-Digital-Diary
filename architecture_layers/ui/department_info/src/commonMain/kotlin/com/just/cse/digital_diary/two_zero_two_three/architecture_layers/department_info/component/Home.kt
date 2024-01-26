package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.department_info.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun Home(
    modifier: Modifier,

) {
    //val message= DepartmentInfoRepository.getDepartmentMessage("1")
    Column(
        modifier = modifier.fillMaxSize()
    ) {
//        DepartmentFromChairmen(
//            imageURL =message.deptImageUrl ,
//            message=message.message
//        )
        Text("DepartmentInfo here...")

    }

}

