package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.components.message_from_vc

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.just.cse.digital_diary.two_zero_two_three.common_ui.WindowSizeDecorator


@Composable
 fun MessageFromVC(
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