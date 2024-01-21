package com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.local_destionations.message_from_vc

import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.just.cse.digital_diary.two_zero_two_three.common_ui.WindowSizeDecorator
import com.just.cse.digital_diary.two_zero_two_three.common_ui.top_bar.SimpleTopBar
import com.just.cse.digitaldiary.twozerotwothree.data.repository.vc_repsoitory.VCInfoRepository

@Composable
internal fun MessageFromVCDestination(onExitRequest: () -> Unit) {
    val info = VCInfoRepository.getVCInfo()
    val message = VCInfoRepository.getMessageFromVC()
    MessageFromVCDestination(
        onExitRequest = onExitRequest,
        vcName = info.name,
        moreInfoOfVC = info.title,
        universityName = info.university,
        messageText = message,
    )

}

@Composable
private fun MessageFromVCDestination(
    onExitRequest: () -> Unit,
    messageText: String,
    vcName: String,
    moreInfoOfVC: String,
    universityName: String,
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

    WindowSizeDecorator(
        onCompact = {
            Scaffold(
                topBar = {
                    SimpleTopBar(
                        onNavigationIconClick = onExitRequest,
                        title = "Message from VC",
                        navigationIcon = Icons.Default.Menu
                    )
                },

                floatingActionButtonPosition = FabPosition.Center
            ) {

                Column(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize()

                        .verticalScroll(rememberScrollState())
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        ViceChancellorImage()

                        Spacer(modifier = Modifier.height(16.dp))

                        NameOfVC(info = vcName)

                        Spacer(modifier = Modifier.height(8.dp))

                        MoreInfoOnVC(info = moreInfoOfVC)

                        Spacer(modifier = Modifier.height(8.dp))

                        UniversityName(info = universityName)

                        Spacer(modifier = Modifier.height(16.dp))

                        MessageSection(
                            titleStyle = titleStyle,
                            bodyStyle = bodyStyle,
                            messageText = messageText
                        )
                    }

                }
            }
        },
        onNonCompact = {
            Column(
                modifier = Modifier
                    .padding(48.dp)
                    .fillMaxSize()

                    .verticalScroll(rememberScrollState())
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    ViceChancellorImage()

                    Spacer(modifier = Modifier.height(16.dp))

                    NameOfVC(info = vcName)

                    Spacer(modifier = Modifier.height(8.dp))

                    MoreInfoOnVC(info = moreInfoOfVC)

                    Spacer(modifier = Modifier.height(8.dp))

                    UniversityName(info = universityName)

                    Spacer(modifier = Modifier.height(16.dp))

                    MessageSection(
                        titleStyle = titleStyle,
                        bodyStyle = bodyStyle,
                        messageText = messageText
                    )
                }

            }

        }
    )


}