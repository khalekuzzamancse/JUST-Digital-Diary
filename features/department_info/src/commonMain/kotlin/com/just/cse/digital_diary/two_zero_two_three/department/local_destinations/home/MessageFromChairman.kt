package com.just.cse.digital_diary.two_zero_two_three.department.local_destinations.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.common_ui.animation.TypeWriter
import com.just.cse.digital_diary.two_zero_two_three.common_ui.network_image.ImageLoader

@Composable
internal fun DepartmentFromChairmen(
    imageURL: String,
    message: String
) {
    Column(
        modifier = Modifier
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.CenterHorizontally),
        ){
            ImageLoader(
                url = imageURL
            )
        }
        Text(
            text = "MESSAGE FROM THE CHAIRMAN",
            style = MaterialTheme.typography.headlineMedium
        )
        TypeWriter(
            text = message,
            delay = 5
        ){
            Text(
                text=it,
                style = MaterialTheme.typography.labelMedium
            )
        }



    }


}