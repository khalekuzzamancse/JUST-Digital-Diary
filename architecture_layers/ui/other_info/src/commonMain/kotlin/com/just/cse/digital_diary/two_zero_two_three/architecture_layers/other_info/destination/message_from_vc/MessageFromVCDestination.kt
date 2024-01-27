package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.destination.message_from_vc

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.model.VCInfoResponseModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.repoisitory.OtherInfoRepository
import com.just.cse.digital_diary.two_zero_two_three.common_ui.WindowSizeDecorator
import com.just.cse.digital_diary.two_zero_two_three.common_ui.top_bar.SimpleTopBar


@Composable
fun MessageFromVCDestination(
    repository: OtherInfoRepository,
    onExitRequest: () -> Unit
) {
    var state by remember {
        mutableStateOf<VCInfoState?>(null)
    }
    LaunchedEffect(Unit){
        val res=repository.getVCInfo()
        if (res is VCInfoResponseModel.Success){
            state= VCInfoState(
                name = res.data.name,
                details = res.data.details,
                message = res.data.message,
                imageUrl = res.data.imageUrl
            )
        }
    }
    state?.let {
        MessageFromVCDestination(
            messageText =it.message,
            vcName = it.name,
            moreInfoOfVC = it.details,
            onExitRequest=onExitRequest,
            imageUrl = it.imageUrl
        )
    }



}

@Composable
private fun MessageFromVCDestination(
    onExitRequest: () -> Unit,
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
    )


}