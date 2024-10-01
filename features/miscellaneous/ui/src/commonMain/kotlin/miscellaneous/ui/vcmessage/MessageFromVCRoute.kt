package miscellaneous.ui.vcmessage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import common.ui.TypeWriter
import common.ui.ImageLoader
import miscellaneous.controller_presenter.UiFactory

@Composable
fun MessageFromVCRoute(token: String?) {
    val viewModel = remember { VCMessageViewmodel(UiFactory.createVCMessageController(token)) }
    val state = viewModel.controller.events.collectAsState().value

    LaunchedEffect(Unit){
        viewModel.controller.fetch()
    }
    state?.let {
        MessageFromVC(
            messageText = it.message,
            vcName = it.name,
            moreInfoOfVC = it.details,
            imageUrl = it.imageUrl
        )
    }

}

@Composable
private fun MessageFromVC(
    imageUrl: String,
    messageText: String,
    vcName: String,
    moreInfoOfVC: String,
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

    Column(
        modifier = Modifier
            .fillMaxSize()

            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            ViceChancellorImage(imageUrl)

            Spacer(modifier = Modifier.height(16.dp))

            NameOfVC(info = vcName)

            Spacer(modifier = Modifier.height(8.dp))

            MoreInfoOnVC(info = moreInfoOfVC)

            Spacer(modifier = Modifier.height(8.dp))


            Spacer(modifier = Modifier.height(16.dp))

            MessageSection(
                titleStyle = titleStyle,
                bodyStyle = bodyStyle,
                messageText = messageText
            )
        }

    }


}

@Composable
internal fun ViceChancellorImage(
    imageUrl: String,
) {
    ImageLoader(
        url = imageUrl,
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
    TypeWriter(text = info, delay = 50) {
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

    TypeWriter(text = messageText, delay = 50) {
        Text(
            text = it,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify,
            modifier = Modifier.fillMaxWidth()
        )

    }

}



