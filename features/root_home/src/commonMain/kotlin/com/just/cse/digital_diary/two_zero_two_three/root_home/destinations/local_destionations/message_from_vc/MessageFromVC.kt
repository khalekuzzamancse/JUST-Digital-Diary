package com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.local_destionations.message_from_vc

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.just.cse.digital_diary.two_zero_two_three.common_ui.animation.TypeWriter
import com.just.cse.digital_diary.two_zero_two_three.common_ui.network_image.ImageLoader


@Composable
internal fun ViceChancellorImage() {
    ImageLoader(
        url = "https://just.edu.bd/images/vc2.jpg",
        modifier = Modifier.height(200.dp)
        .fillMaxWidth()

    )

}

@Composable
internal fun NameOfVC(info: String) {
    Text(
        text = info,
        style = TextStyle(
            fontSize = 20.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        ),
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
internal fun MoreInfoOnVC(info: String) {
    Text(
        text = info,
        style = MaterialTheme.typography.bodySmall,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
internal fun UniversityName(info: String) {
    TypeWriter(text=info, delay = 50){
        Text(
            text = it,
            style = MaterialTheme.typography.titleSmall,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }

}

@Composable
internal fun MessageSection(
    titleStyle: TextStyle,
    bodyStyle: TextStyle,
    messageText: String
) {
    Text(
        text = "Message",
        style = MaterialTheme.typography.headlineLarge,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(8.dp))

    TypeWriter(text=messageText, delay = 50){
        Text(
            text = it,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify,
            modifier = Modifier.fillMaxWidth()
        )

    }

}



