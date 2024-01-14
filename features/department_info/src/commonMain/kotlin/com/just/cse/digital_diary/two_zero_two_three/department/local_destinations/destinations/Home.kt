package com.just.cse.digital_diary.two_zero_two_three.department.local_destinations.destinations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.random.Random
@Composable
internal fun DepartmentHomeDestinationContent(
    modifier: Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        DepartmentFromChairmen()

    }

}

@Composable
private fun DepartmentFromChairmen() {
    Column(
        modifier = Modifier
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .background(Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)))
                .align(Alignment.CenterHorizontally),
        )
        Text(
            text = "MESSAGE FROM THE CHAIRMAN",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text="Welcome to the Department of Biomedical Engineering (BME) " +
                    "at Jashore University of Science and Technology (JUST). Our department was founded in 2016 and we want" +
                    " to create an environment of educational excellence, both in academic and research." +
                    "You’ll find a lively academic atmosphere in our department—including our faculty members, amiable staffs and specifically charming undergraduate students. Our teachers take pleasure in conveying their knowledge and intellectual " +
                    "passion to students. They also encourage students in thoughtful activities and ",
            style = MaterialTheme.typography.labelMedium
        )


    }


}