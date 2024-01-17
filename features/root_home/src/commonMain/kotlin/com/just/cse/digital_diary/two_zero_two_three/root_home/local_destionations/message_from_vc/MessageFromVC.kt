package com.just.cse.digital_diary.two_zero_two_three.root_home.local_destionations.message_from_vc

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.just.cse.digital_diary.two_zero_two_three.common_ui.network_image.ImageLoader
import com.just.cse.digital_diary.two_zero_two_three.common_ui.animation.TypeWriter
import com.just.cse.digital_diary.two_zero_two_three.common_ui.top_bar.SimpleTopBar




@Composable
fun ColumnScope.ViceChancellorImage() {
    ImageLoader(
        url = "https://just.edu.bd/images/vc2.jpg",
        modifier = Modifier.height(200.dp)
        .fillMaxWidth()

    )

}

@Composable
fun NameOfVC(info: String) {
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
fun MoreInfoOnVC(info: String) {
    Text(
        text = info,
        style = MaterialTheme.typography.bodySmall,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun UniversityName(info: String) {
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
fun MessageSection(
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



