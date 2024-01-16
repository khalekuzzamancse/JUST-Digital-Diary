package com.just.cse.digital_diary.features.departments.navgraph.local_destinations.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.common_ui.animation.TypeWriter
import com.just.cse.digital_diary.two_zero_two_three.common_ui.network_image.ImageLoader
import com.just.cse.digitaldiary.twozerotwothree.data.repository.faculty_info.FacultyInfoRepository

@Composable
internal fun HomeContent() {
    val message = FacultyInfoRepository.getFacultyMessage("1")
    FacultyInfo(
        imageURL = message.imageUrl,
        message = message.message,
        modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())
    )
}

@Composable
private fun FacultyInfo(
    modifier :Modifier= Modifier,
    imageURL: String,
    message: String,
) {
    Column(
        modifier = modifier
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.CenterHorizontally),
        ) {
            ImageLoader(
                url = imageURL
            )
        }
        Text(
            text = "Message From Dean",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )
        TypeWriter(
            text = message,
            delay = 5
        ) {
            Text(
                text = it,
                style = MaterialTheme.typography.labelMedium
            )
        }


    }


}