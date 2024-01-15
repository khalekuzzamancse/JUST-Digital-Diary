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
import com.just.cse.digital_diary.features.common_ui.network_image.ImageLoader
import com.just.cse.digital_diary.features.common_ui.animation.TypeWriter

data class ViceChancellorInfo(
    val name: String,
    val title: String,
    val university: String
)

@Composable
fun ViceChancellorMessage(
    onExitRequest:()->Unit,
) {
    val titleStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        color = Color.Black
    )

    val bodyStyle = TextStyle(
        fontSize = 18.sp,
        color = Color.Gray
    )

    val viceChancellorInfo = ViceChancellorInfo(
        name = "Mr. X",
        title = "Vice Chancellor",
        university = "Jashore University of Science and Technology"
    )

    val messageText = """
        Jashore University of Science and Technology (JUST) has started a steady journey of reaching a new height of excellence in research and to achieve a unique milestone in promoting new ideas and innovation, and in serving the nation and the global community by creating enlightened and skilled professionals who can meet the challenges of the 21st century fostering the motto of ‘being the employer, not the employee’. In keeping with this purpose, JUST has already been declared a research university that aims at generating and advancing knowledge by cutting-edge research in its state-of-the-art laboratories and in the congenial academic ambience. Apart from these, JUST has international and local collaboration with a wide range of reputed academia and industry...
    """.trimIndent()
    Scaffold(
        topBar = {
            MessageFromVCTopBar(
                onNavigationIconClick = onExitRequest,
                title = "Message from VC"
            )
        },

        floatingActionButtonPosition = FabPosition.Center
    ) {

        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()

                .verticalScroll(rememberScrollState())
        ){
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                ViceChancellorImage()

                Spacer(modifier = Modifier.height(16.dp))

                NameOfVC(info = viceChancellorInfo.name)

                Spacer(modifier = Modifier.height(8.dp))

                MoreInfoOnVC(info = viceChancellorInfo.title)

                Spacer(modifier = Modifier.height(8.dp))

                UniversityName(info = viceChancellorInfo.university)

                Spacer(modifier = Modifier.height(16.dp))

                MessageSection(
                    titleStyle = titleStyle,
                    bodyStyle = bodyStyle,
                    messageText = messageText
                )
            }

        }
    }



}

@Composable
private fun ColumnScope.ViceChancellorImage() {
    ImageLoader(
        url = "https://just.edu.bd/images/vc2.jpg",
        modifier = Modifier.height(200.dp)
        .fillMaxWidth()

    )

}

@Composable
private fun NameOfVC(info: String) {
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
private fun MoreInfoOnVC(info: String) {
    Text(
        text = info,
        style = MaterialTheme.typography.bodySmall,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun UniversityName(info: String) {
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
private fun MessageSection(
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



