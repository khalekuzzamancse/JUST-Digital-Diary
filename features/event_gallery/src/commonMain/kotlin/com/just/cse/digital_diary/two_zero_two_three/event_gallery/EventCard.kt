package com.just.cse.digital_diary.two_zero_two_three.event_gallery

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.common_ui.network_image.ImageLoader

@Composable
internal fun EventCard(
    eventName:String,
    eventDetails:String,
    eventImageLink:String
) {
    Surface(
        modifier = Modifier,
        shadowElevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = eventName,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(Modifier.height(4.dp))
            ImageLoader(
                url = eventImageLink,
                modifier = Modifier
                    .heightIn(max = 300.dp)
                    .widthIn(max = 300.dp)
                    .align(Alignment.CenterHorizontally),
            )
        }
    }
}